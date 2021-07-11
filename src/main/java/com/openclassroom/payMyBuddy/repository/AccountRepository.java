package com.openclassroom.payMyBuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.BankTransaction;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByIban(String iban);

	Account findByEmail(String email);

	Account findUserById( Long id);
//	List<Account> findByUserId(Long id);




}
