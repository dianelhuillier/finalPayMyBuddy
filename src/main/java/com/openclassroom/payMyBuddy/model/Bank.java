package com.openclassroom.payMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id")
	private Long id;
    @Column(name = "bank_name", nullable = false)
    @NonNull
	private String bankName;



}
