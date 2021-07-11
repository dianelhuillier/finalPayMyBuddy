package com.openclassroom.payMyBuddy.service;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassroom.payMyBuddy.model.Connection;
import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.repository.ConnectionRepository;
import com.openclassroom.payMyBuddy.repository.TransactionPayMyBuddyRepository;
import com.openclassroom.payMyBuddy.repository.UserRepository;

@Service
public class TransactionService implements ITransactionService{

	@Autowired
	TransactionPayMyBuddyRepository transactionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ConnectionRepository connectionRepository;

	@Override
	public User connectingPeople(String emailUser, String emailConnection) {
		User connectUser = userRepository.findByEmail(emailUser);
		Connection connectedUser = connectionRepository.findByEmail(emailConnection); //search for connection object with email
		Set<Connection> listConnectedUsersConnection= connectUser.getConnections(); //
		listConnectedUsersConnection.add(connectedUser);
		connectUser.setConnections(listConnectedUsersConnection);

		return userRepository.save(connectUser);
	}

	@Transactional 
	@Override
	public int transfertMoney(TransactionPayMyBuddy transactionPayMyBuddy) {

		User senderUser = userRepository.findByEmail(transactionPayMyBuddy.getEmailUser()); //on créee un user
		User receiveUser = userRepository.findByEmail(transactionPayMyBuddy.getEmailConnection()); //on recupere le partenaire
		
		
		User adminUser = userRepository.findByEmail("diane@admin.fr");//on appelle le compte admin ET on le trouve
		
		double soldAvailable = senderUser.getSold();
		double soldUp = receiveUser.getSold();
		if (soldAvailable < transactionPayMyBuddy.getAmount()) {
			System.out.println("amount too low");
			return 0;
		}else  {

			double soldLeft = soldAvailable - transactionPayMyBuddy.getAmount();
			senderUser.setSold(soldLeft);     
			
			double soldPresent = transactionPayMyBuddy.getAmount();
					
			double soldTransition = soldPresent + soldUp; 
			
			double prelevement = transactionPayMyBuddy.getAmount() *5/1000;		
			
			soldUp = soldTransition-prelevement;  		
			
			double lionPart = soldTransition-soldUp;
			double actualSoldAdmin = adminUser.getSold();
			double finalSoldAdmin = lionPart+actualSoldAdmin;
			adminUser.setSold(finalSoldAdmin);
			
			receiveUser.setSold(soldUp);

			//on save les new users
			userRepository.save(senderUser);
			userRepository.save(receiveUser);
			transactionRepository.save(transactionPayMyBuddy);
		return 1;	
		}
	}

	
	@Override
	public Page<TransactionPayMyBuddy> findTransactionByEmailUser(String emailUser, int page, int taille) {
		final Pageable pageable = PageRequest.of(page, taille, Sort.by(Sort.Direction.DESC,"id"));

		return transactionRepository.findByEmailUser(emailUser, pageable);
	}

    public long getTransactionsCount() {
        return transactionRepository.count();
    }
 
    
    
    
//    public Page<TransactionPayMyBuddy> getPaginatedTransactions(final int pageNumber, final int pageSize) {
//        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
//        return transactionRepository.findAll(pageable);
//    }
//
//	@Override
//	public List<TransactionPayMyBuddy> getAllTransactions(Integer pageNo, Integer pageSize, String sortBy) {
//		return transactionRepository.findAll();
//	}


//code jaava
    public List<TransactionPayMyBuddy> listAll() {
        return transactionRepository.findAll();
    } 
	
//    public Page<TransactionPayMyBuddy> listAll(int pageNum) {
//        int pageSize = 5;
//         
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//         
//        return transactionRepository.findAll(pageable);
//    }
    
//	public  List<TransactionPayMyBuddy> getAllTransactions(Integer pageNo, Integer pageSize, String sortBy)
//    {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
// //, Sort.by(sortBy)
//        Page<TransactionPayMyBuddy> pagedResult = transactionRepository.findAll(paging);
//         
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<TransactionPayMyBuddy>();
//        }
//    }
	
	public Page<TransactionPayMyBuddy> listTransactionsByPageBySize(int page, int taille) {

		final Pageable pageable = PageRequest.of(page, taille, Sort.by(Sort.Direction.DESC,"id"));

		return transactionRepository.findAll(pageable);
	}
	

}
