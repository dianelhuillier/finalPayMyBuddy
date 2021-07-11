package com.openclassroom.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.payMyBuddy.model.BankTransaction;

@Repository
public interface TransactionBankRepository extends JpaRepository<BankTransaction, Long>{

	BankTransaction findByEmail(String email);
}
