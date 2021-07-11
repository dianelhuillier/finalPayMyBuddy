package com.openclassroom.payMyBuddy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;

@Repository
public interface TransactionPayMyBuddyRepository extends JpaRepository<TransactionPayMyBuddy, Long> {
	
	Page<TransactionPayMyBuddy> findByEmailUser(String emailUser, Pageable pageable);

//	@Override
//	Page<TransactionPayMyBuddy> findAll(Pageable pageable);

}
