package com.openclassroom.payMyBuddy.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionPayMyBuddy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @Column(name = "email_user", nullable = false)
	private String emailUser;
    @Column(name = "amount", nullable = false)
	private double amount;
	
    @Column(name = "email_connection", nullable = false)
	private String emailConnection;

	private String timeStamp;
	
	private String description;
	
			
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_transaction", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private Set<TransactionPayMyBuddy> transactions;
}
