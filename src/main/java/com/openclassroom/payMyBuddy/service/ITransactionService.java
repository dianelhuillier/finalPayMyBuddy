package com.openclassroom.payMyBuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;


public interface ITransactionService {

	
	User connectingPeople(String emailUser, String emailConnection);
	
	int transfertMoney(TransactionPayMyBuddy transactionPayMyBuddy);
	
	Page<TransactionPayMyBuddy> findTransactionByEmailUser(String emailUser, int page, int taille);

	

	//List<TransactionPayMyBuddy> getAllTransactions(Integer pageNo, Integer pageSize, String sortBy);

	//Page<TransactionPayMyBuddy> listAll(int pageNum);

//	Page<TransactionPayMyBuddy> listTransactionsByPageBySize(int page, int taille);

}
