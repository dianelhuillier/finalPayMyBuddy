package com.openclassroom.payMyBuddy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.BankTransaction;

public interface ITransactionBankService {

	int sendBankMoney(BankTransaction bankTransaction);

	List<BankTransaction> findTransactionByEmailUser(String emailUserAuth);

	Account connectingAccount(String emailUserAuth, String iban);





}
