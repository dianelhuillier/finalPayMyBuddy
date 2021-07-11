package com.openclassroom.payMyBuddy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.BankTransaction;
import com.openclassroom.payMyBuddy.model.Connection;
import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.AccountRepository;
import com.openclassroom.payMyBuddy.service.AccountService;
import com.openclassroom.payMyBuddy.service.IAccountService;
import com.openclassroom.payMyBuddy.service.ITransactionBankService;
import com.openclassroom.payMyBuddy.service.IUserService;

import org.springframework.ui.Model;

@Controller
public class BankTransactionController {
	
	@Autowired
	IAccountService iAccountService;
	@Autowired
	IUserService iUserService;
	@Autowired
	ITransactionBankService iTransactionBankService;
	@Autowired
	AccountRepository accountRepository;
	
	
	
	private String messageError ="";
	

    
	@GetMapping("/registreiban")
	public String showIban (Model model) {
		Account account = new Account();
		model.addAttribute("newAccount", account);
		double newSold = account.getSoldAccount();
		model.addAttribute("newSold", newSold);
		return "findIban";
	}

	
	@PostMapping("/registreiban")
	public String processIban (@ModelAttribute Account newAccount, Model model) {

			iAccountService.saveAccount(newAccount);
			Account account = new Account();

			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String emailUserAuth = auth.getName();
			User userAuth = iUserService.findUserByEmail(emailUserAuth);
			Set<Account> listAccount = userAuth.getAccounts();

		model.addAttribute("newAccount", account);
		model.addAttribute("messageError", "Account has been registered successfully");
		model.addAttribute("listAccount",listAccount);
		return "findIban";
	}
	

	@GetMapping("/bankTransfer")
	public String showBankTransfer (Model model) {

		//recuperation de l'utilisateur authentifié		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);
		Set<Account> listAccount = userAuth.getAccounts();
		System.out.println(userAuth.getAccounts());
		//initialisation des champs du formulaire avec l'object new Transaction
		BankTransaction bankTransaction = new BankTransaction();
		model.addAttribute("newBankTransaction", bankTransaction);
		
		//recupération de la liste de toutes les transactions enregistrées par l'utilisateur authentifié
		List<BankTransaction> listAllBankTransactions = iTransactionBankService.findTransactionByEmailUser(emailUserAuth);

		List<String>listStringIbans = listAllBankTransactions.stream().map(BankTransaction::getIban).collect(Collectors.toList());

		model.addAttribute("listAllBankTransactions", listAccount);
		model.addAttribute("listStringIbans", listStringIbans);

		model.addAttribute("messageError", messageError);
	return "transfer";
}
	
	
	@PostMapping("/bankTransfer")
	public String processBankTranfer(@ModelAttribute BankTransaction newBankTransaction, Model model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		newBankTransaction.setEmail(emailUserAuth);
		System.out.println(newBankTransaction.toString());
		

		int echec  = iTransactionBankService.sendBankMoney(newBankTransaction);
		if (echec == 0) {
			messageError = "Your account isn't accredit enought";
		    return "redirect:transfer";
		    
		} else {
			messageError = 	"Your Transaction has been completed successfully";
			return "redirect:transfer";
		}	     
	}
	
	
	
	@GetMapping(value = "/account/details")
	public String accountDetails(
	    @RequestParam(value = "iban") String iban,
	    @RequestParam(value = "soldAccount") double soldAccount){
	    return "findIban";
	}
	
}



