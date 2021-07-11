package com.openclassroom.payMyBuddy.service;

import java.util.Arrays;
import com.openclassroom.payMyBuddy.service.EmailValidation;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroom.payMyBuddy.model.Connection;
import com.openclassroom.payMyBuddy.model.Role;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.ConnectionRepository;
import com.openclassroom.payMyBuddy.repository.RoleRepository;
import com.openclassroom.payMyBuddy.repository.UserRepository;

@Service
public class UserService implements IUserService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConnectionRepository connectionRepository;



	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, ConnectionRepository connectionRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.connectionRepository = connectionRepository;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}



	public User saveUser(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setSold(0);
		final Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		Connection connection =new Connection();
		connection.setEmail(user.getEmail());

		connectionRepository.save(connection);

		//	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}





}


