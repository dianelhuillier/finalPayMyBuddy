package com.openclassroom.payMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.BankTransaction;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.AccountRepository;
import com.openclassroom.payMyBuddy.repository.UserRepository;

@Service
public class AccountService implements IAccountService{


	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final 	IUserService iUserService;


	@Autowired
	public AccountService(AccountRepository accountRepository, UserRepository userRepository, IUserService iUserService
			) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.iUserService = iUserService;

	}

	public Account findAccountByUserEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	public Account saveAccount(Account account) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);

		account.setEmail(userAuth.getEmail());
		account.setSoldAccount(0);
		return accountRepository.save(account);
	}


	public Account findAccountByUserIban(String iban) {

		return accountRepository.findByIban(iban);
	}

	@Override
	public Account findTransactionByEmailUser(String emailUser) {
		return accountRepository.findByEmail(emailUser);
	}


	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}


}

