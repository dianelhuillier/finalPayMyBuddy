package com.openclassroom.payMyBuddy.service;

import java.util.List;

import com.openclassroom.payMyBuddy.model.Account;

public interface IAccountService {

	Account findAccountByUserIban(String iban);

	Account saveAccount(Account account);

	Account findTransactionByEmailUser(String emailUser);

//	List<Account> listAccounts(String iban, double soldAccount);
	
	List<Account> findAll();

}
