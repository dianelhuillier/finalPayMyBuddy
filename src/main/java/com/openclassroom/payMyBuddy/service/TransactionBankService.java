package com.openclassroom.payMyBuddy.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.BankTransaction;
import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.AccountRepository;
import com.openclassroom.payMyBuddy.repository.TransactionBankRepository;
import com.openclassroom.payMyBuddy.repository.UserRepository;
@Service
public class TransactionBankService implements ITransactionBankService{


	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionBankRepository transactionBankRepository;
	
	
	@Transactional 
	@Override
	public int sendBankMoney(BankTransaction bankTransaction) {
		
		User senderUser = userRepository.findByEmail(bankTransaction.getEmail()); //on créee un user
		System.out.println("senderUser : " + senderUser);
		
		Account bankAccount = accountRepository.findByEmail(bankTransaction.getEmail());
		
		
		double soldBank = bankAccount.getSoldAccount();
		System.out.println("bankAccount : " + bankAccount);
		
		bankTransaction.getAmount();
		System.out.println("bankamount : " + bankTransaction.getAmount()); //fonctionne
		
		double soldApp = senderUser.getSold();
		if (soldApp < bankTransaction.getAmount()) {
		System.out.println("amount too low");
		return 0;
	} else {
		double soldLeft = soldApp - bankTransaction.getAmount();
		double finalSoldBank = soldBank + bankTransaction.getAmount();
		senderUser.setSold(soldLeft);  
		
		
		bankAccount.setSoldAccount(finalSoldBank);
		
//		accountRepository.save(bankAccount);

		userRepository.save(senderUser);
		transactionBankRepository.save(bankTransaction);
		
		
		return 1;
	}
	}

	
	
	
	
	@Transactional 
	public int getBankMoney(BankTransaction bankTransaction){
		return 0;
		
	}
	
	
	
	
	
	
    public List<BankTransaction> listAll() {
        return transactionBankRepository.findAll();
    }


	@Override
	public List<BankTransaction> findTransactionByEmailUser(String emailUserAuth) {
		return transactionBankRepository.findAll();
	}


	
	
	
	
//    public ForeignTransaction createTransaction(ForeignTransaction foreignTransaction) throws Exception {
//        Account account = accountRepository.findByIban(foreignTransaction.getEmitterIban());
//        if (account.getSold() >= foreignTransaction.getAmount()) {
//            account.setSold(account.getSold() - foreignTransaction.getAmount());
//            accountRepository.save(account);
//            foreignTransaction.setDate(LocalDate.now());
//            return foreignTransactionRepository.save(foreignTransaction);
//        } else {
//            throw new Exception("Not enough money on account");
//        }
//
//    }
//	
//	
//    public List<Account> listOfAccounts(User user) {
//        return accountRepository.findByUserId(user.getId());
//    }
	
	
	@Override
	public Account connectingAccount(String emailUserAuth, String iban) {
		User connectUser = userRepository.findByEmail(emailUserAuth);
		Account newAccount = accountRepository.findByEmail(emailUserAuth);
		
		Set<Account> listAccountsRegistred = connectUser.getAccounts();
		
		listAccountsRegistred.add(newAccount);
		
		connectUser.setAccounts(listAccountsRegistred);
		return accountRepository.save(newAccount);
	}


	
	
}
