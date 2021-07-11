package com.openclassroom.payMyBuddy.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankTransaction_id")
	private Long id;
	
    @Column(name = "email", nullable = false)
	private String email;
    
    @Column(name = "amount", nullable = false)
	private double amount;

    @Column(name= "date")
    private LocalDate date;
    
    private String bank_description;
    
    @Column(name ="iban", nullable = false, unique = false)
    private String iban;

    
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_bankTransaction", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "bankTransaction_id"))
    private Set<BankTransaction> bankTransactions;





}
