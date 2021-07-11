package com.openclassroom.payMyBuddy;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassroom.payMyBuddy.model.Account;
import com.openclassroom.payMyBuddy.model.Connection;
import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.AccountRepository;
import com.openclassroom.payMyBuddy.repository.ConnectionRepository;
import com.openclassroom.payMyBuddy.repository.RoleRepository;
import com.openclassroom.payMyBuddy.repository.TransactionPayMyBuddyRepository;
import com.openclassroom.payMyBuddy.repository.UserRepository;
import com.openclassroom.payMyBuddy.service.AccountService;
import com.openclassroom.payMyBuddy.service.TransactionService;
import com.openclassroom.payMyBuddy.service.UserService;

@SpringBootTest
class PayMyBuddyApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	ConnectionRepository connectionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	TransactionService transactionService;
	@Autowired
	TransactionPayMyBuddyRepository transactionRepository;
@Autowired
AccountRepository accountRepository;
@Autowired
AccountService accountService;


	@Test
	void contextLoads() {
		
	}

//	@Test
//	public void testSaveAccount() {
//		
//		Account testAccount = new Account((long)1,"fr76", "test@test.fr", 0);
//accountService.saveAccount(testAccount);
//		assertTrue(accountRepository.findEmail().equals("test@test.fr"));
//	}
	
}
	//
//
//
//
//
//	//TransactionService
//
//	
//	//getcontent()
//	
////	@Test
////	public void testConnectingPeople() {
////		User dianeUser = userRepository.findByEmail("diane@admin.fr");
////		User testUser = new User((long) 10, "user", "test", "test@test.fr", "1234", 0, 0, null, null);
////		User testConnection = transactionService.connectingPeople("diane@admin.fr", "test@test.fr");
////		Connection connectedUser = connectionRepository.findByEmail("test@test.fr"); //search for connection object with email
////
////		//Connection connection = new Connection();
////
////		Set<Connection> userConnnection = testConnection.getConnections();
////		//connection.setEmail("test@test.fr");
////		//when(connectionRepository.save(connection)).thenReturn(connection);
////		userConnnection.add(connectedUser);
////		//		connectionRepository.findByEmail("test@test.fr");
////
////		//Connection connectedUser = connectionRepository.findByEmail("test@test"); 
////		//Set<Connection> listConnectedUsersConnection= dianeUser.getConnections();
////
////		//listConnectedUsersConnection.add(connectedUser);
////		//dianeUser.setConnections(listConnectedUsersConnection);
////		//List<String>list = listConnectedUsersConnection.stream().map(Connection::getEmail).collect(Collectors.toList());
////		assertTrue(userConnnection.stream().anyMatch(s->s.equals("test")));
////	}
//
//	@Test
//	public void testTransferMoney(){
//		User senderUser = userRepository.findByEmail("diane@admin.fr");
//		User receiveUser = new User((long) 10, "user", "test", "test@test.fr", "1234", 0, 0, null, null, null);
//		//User receiveUser = userRepository.findByEmail(transactionPmb.getEmailConnection()); //on recupere le partenaire
//
//		receiveUser.setSold(100);
//		double user1SoldInitial = senderUser.getSold();
//		double user2SoldInitial = receiveUser.getSold();
//		System.out.println(user2SoldInitial);
//
//		if(senderUser.getSold() >= 10) {
//
//			TransactionPayMyBuddy transaction = new TransactionPayMyBuddy((long)12, "diane@admin.fr", 10, "test@test.fr", null, null, null);
//			transactionService.transfertMoney(transaction);
//
//
//			double amount = transaction.getAmount();
//
//			double user2SoldFinal = user2SoldInitial -amount;
//			System.out.println(user2SoldFinal);
//			double differenceSold2 = user2SoldInitial - user2SoldFinal;
//			double difference = amount * 5 /1000;
//			double lionPart = amount - difference;
//			assertEquals(differenceSold2, amount);	
//		}else {
//			System.out.println("amount too low");
//
//		}
//	}
//
//	@Test
//	public void testFindTransactionByEmailUser() {
//		List<TransactionPayMyBuddy> list = transactionService.findTransactionByEmailUser("diane@admin.fr", 0, 10).getContent();
//		List<Set<TransactionPayMyBuddy>> listFinal = list.stream().map(TransactionPayMyBuddy::getTransactions).collect(Collectors.toList());
//		org.junit.Assert.assertNotNull(listFinal);
//	}
// 
//	@Test
//	public void testGetTransactionsCount() {
//		double count = transactionService.getTransactionsCount();
//		org.junit.Assert.assertNotNull(count);
//	}
//
//
//
//
//
//
//
//
//	@Test 
//	public void testSaveThisUser() {
//		User user = new User((long) 10, "user", "test", "test@test.fr", "1234", 0, 0, null, null, null);
//		assertTrue(user.getEmail().equals("test@test.fr"));
//		assertTrue(user.getFirstName().equals("user"));
//		assertTrue(user.getLastName().equals("test"));
//
//	}
//
//	//UserRepository
//
//	@Test
//	public void testFindByEmail() {
//		User user = userRepository.findByEmail("diane@admin.fr");
//		String testEmail = user.getFirstName();
//		assertTrue(testEmail.equals("diane"));
//	}
//
//	@Test
//	public void testFindById() {
//		User user = userRepository.findById((long) 12);
//		String testId = user.getEmail();
//		assertTrue(testId.equals("diane@admin.fr"));
//	}
//
//	//UserService
//
//	@Test
//	public void testFindUserByEmail() {
//		User user = userService.findUserByEmail("diane@admin.fr");
//		String testUserEmail = user.getFirstName();
//		assertTrue(testUserEmail.equals("diane"));
//	}
//
//	@Test
//	public void testSaveUser() {
//		User user = new User((long)10, "test", "test", "test@save.fr", "1234", 0, 0, null, null, null);
//
//		Connection connection =new Connection();
//		connection.setEmail(user.getEmail());
//		connectionRepository.save(connection);
//
//		userService.saveUser(user);
//
//		//Connection testConnection = connectionRepository.getById((long)10);
//		String testEmail = connection.getEmail();
//		assertTrue(testEmail.equals("test@save.fr"));
//	}
//}
