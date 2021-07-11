package com.openclassroom.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.payMyBuddy.model.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long>{

	Connection findByEmail(String email);
	

}
