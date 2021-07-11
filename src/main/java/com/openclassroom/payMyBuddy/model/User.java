package com.openclassroom.payMyBuddy.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")


public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private Long id;
    @Column(name = "name", nullable = false)
    @NonNull
	private String firstName;
    @Column(name = "last_name", nullable = false)
    @NonNull
	private String lastName;
    @Column(name = "email", nullable = false)
    @NonNull
    @Email (message = "Email should be valid")
    private String email;
    @Column(name = "password", nullable = false)
    @NonNull
    private String password;
    @Column(name = "active")
	private int active;
	private double sold;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_account", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
 
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_connection", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "connection_id"))
    private Set<Connection> connections;
 

	
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }



	public User(Long id, @NonNull @Email(message = "Email should be valid") String email, Set<Account> accounts) {
		super();
		this.id = id;
		this.email = email;
		this.accounts = accounts;
	}

    

}
