package com.openclassroom.payMyBuddy.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

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
import com.openclassroom.payMyBuddy.model.dto.AccountDTO;
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
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);
		
		Set<Account> listAccount = userAuth.getAccounts();
		List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
		for (Account account : listAccount) {
			Account accountAdd = iAccountService.findAccountByUserEmail(account.getEmail());
			AccountDTO accountDTO = new AccountDTO(accountAdd.getIban(), accountAdd.getEmail(), accountAdd.getSoldAccount());
			System.out.println(accountAdd.getIban());
			listAccountDTO.add(accountDTO);
		}
		
		model.addAttribute("listAccount", listAccountDTO);

		Account account = new Account();
		model.addAttribute("newAccount", account);
		return "findIban";
	}


	@PostMapping("/registreiban")
	public String processIban (@ModelAttribute Account newAccount, Model model) {

		iAccountService.saveAccount(newAccount);

		String email = newAccount.getEmail();
		final Account accountExists = iAccountService.findAccountByUserEmail(email);

		if (accountExists == null) {
			model.addAttribute("messageError", "The user isn't existing");
			return "findIban";
		} else {

			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String emailUserAuth = auth.getName();
			User userAuth = iUserService.findUserByEmail(emailUserAuth);

			iTransactionBankService.connectingAccount(emailUserAuth, newAccount.getIban());
			Set<Account> listAccount = userAuth.getAccounts();

			//	List<String>list = listAccount.stream().map(Account::getIban).collect(Collectors.toList());

			model.addAttribute("messageError", "Account has been registered successfully");
			model.addAttribute("listAccount",listAccount);
			return "findIban";
		}
	}

	@GetMapping("/bankTransfer")
	public String showBankTransfer (Model model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);

		Set<Account> listAccount = userAuth.getAccounts();
		model.addAttribute("listAccount", listAccount);

		BankTransaction bankTransaction = new BankTransaction();
		model.addAttribute("newBankTransaction", bankTransaction);

		List<BankTransaction> listAllBankTransactions = iTransactionBankService.findTransactionByEmailUser(emailUserAuth);

		model.addAttribute("listAllBankTransactions", listAllBankTransactions);
		model.addAttribute("messageError", messageError);
		return "findIban";
	}


	@PostMapping("/bankTransfer")
	public String processBankTranfer(@ModelAttribute BankTransaction newBankTransaction, Model model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		Account account = accountRepository.findByEmail(emailUserAuth);

		String iban = account.getIban();

		newBankTransaction.setEmail(emailUserAuth);
		newBankTransaction.setDate(LocalDate.now());
		newBankTransaction.setIban(iban);

		int echec  = iTransactionBankService.sendBankMoney(newBankTransaction);
		if (echec == 0) {
			messageError = "Your account isn't accredit enought";
			return "findIban";

		} else {
			messageError = 	"Your Transaction has been completed successfully";
			return "findIban";
		}	     
	}


	@GetMapping(value = "/findIban")
	public String accountDetails(){
		return "findIban";
	}

	

}



