package com.openclassroom.payMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "connection")
public class Connection {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "connection_id")
	private Long Id;
    @Column(name = "email", nullable = false)
	private String email;
	
}
