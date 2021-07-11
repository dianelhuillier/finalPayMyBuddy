package com.openclassroom.payMyBuddy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassroom.payMyBuddy.model.Connection;
import com.openclassroom.payMyBuddy.model.TransactionPayMyBuddy;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.model.dto.UserDto;
import com.openclassroom.payMyBuddy.repository.TransactionPayMyBuddyRepository;
import com.openclassroom.payMyBuddy.service.ITransactionService;
import com.openclassroom.payMyBuddy.service.IUserService;

@Controller
public class TransactionController {

	@Autowired
	ITransactionService iTransactionService;
	@Autowired
	IUserService iUserService;
	@Autowired
	TransactionPayMyBuddyRepository transactionRepository;
	
	private String messageError ="";
	
	@GetMapping("/findConnection")
	public String showFindConnection (Model model) {
		
		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);
		
		Set<Connection> listConnected = userAuth.getConnections();
		List<UserDto> listUsersDto= new ArrayList<UserDto>();
		for (Connection connection : listConnected) {
			User userConnection = iUserService.findUserByEmail(connection.getEmail());
			UserDto userDTO = new UserDto(userConnection.getFirstName(), userConnection.getLastName(), userConnection.getEmail());
			listUsersDto.add(userDTO);
			
		}
		model.addAttribute("listConnectedPeople", listUsersDto);
		
		
		Connection connection = new Connection();
		model.addAttribute("newConnection", connection);
	return "findConnection";
	}
	
	
	@PostMapping("/findConnection")
	public String processConnection(@ModelAttribute Connection newConnection, Model model) {

		final User userExists = iUserService.findUserByEmail(newConnection.getEmail());

		if (userExists == null) {
			model.addAttribute("messageError", "The user isn't existing");
		    return "findConnection";
		    
		} else {
			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String emailUserAuth = auth.getName();
			iTransactionService.connectingPeople(emailUserAuth, newConnection.getEmail());

			return "redirect:findConnection";
		}	     
	}
	
	@GetMapping("/transfer")
	public String showTransfer (Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

		//recuperation de l'utilisateur authentifié		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		User userAuth = iUserService.findUserByEmail(emailUserAuth);
		
		// recuperation de la liste des utilisateurs connectés au l'authentifié
		Set<Connection> listConnected = userAuth.getConnections();
		model.addAttribute("listConnectedPeople", listConnected);
		System.out.println(listConnected); //fonctionne
		//initialisation des champs du formulaire avec l'object new Transaction
		TransactionPayMyBuddy transaction = new TransactionPayMyBuddy();
		model.addAttribute("newTransactionPayMyBuddy", transaction);
		
		//recupération de la liste de toutes les transactions enregistrées par l'utilisateur authentifié
		Page<TransactionPayMyBuddy> pages = iTransactionService.findTransactionByEmailUser(emailUserAuth, page, 3);

		List<TransactionPayMyBuddy> listAllTransactions = pages.getContent();
		
		model.addAttribute("nombrePages", new int[pages.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("listAllTransactions", listAllTransactions);
		model.addAttribute("messageError", messageError);
	return "transfer";
}
	
	
//	@GetMapping(value={"/listeAlertes" ,"/"})
//	public String envoyerAlerte(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
//		
//	
//		Page<Alerte> pages = alerteService.listAlerteByPageAndTaille(page, 3);
//
//		
//		List<Alerte> listAlerte = pages.getContent();
//		
//		model.addAttribute("listeAlerte", listAlerte);
//		
//		model.addAttribute("nombrePages", new int[pages.getTotalPages()]);
//		
//		model.addAttribute("currentPage", page);
//		
//		return "listeAlertes";
//	}
	
	
	
	
	
	
	@PostMapping("/transfer")
	public String processTranfer(@ModelAttribute TransactionPayMyBuddy newTransactionPayMyBuddy, Model model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUserAuth = auth.getName();
		newTransactionPayMyBuddy.setEmailUser(emailUserAuth);
		System.out.println(newTransactionPayMyBuddy.toString());
		

		int echec  = iTransactionService.transfertMoney(newTransactionPayMyBuddy);
		if (echec == 0) {
			messageError = "Your account isn't accredit enought";
		    return "redirect:transfer";
		    
		} else {
			messageError = 	"Your Transaction has been completed successfully";
			return "redirect:transfer";
		}	     
	}
	
	//TODO : lister les return
	// les paginations   https://www.baeldung.com/spring-data-jpa-pagination-sorting
	// prelever 5%     OK 
	//faire les tests
	// nettoyer le code
	//trouver le plugin diagramme de classes      OK
	
//    @GetMapping(value = "/transfer/paginated/{page}/{page-size}")
//    public ResponseEntity<List<TransactionPayMyBuddy>> getAllTransactions(
//                        @RequestParam(defaultValue = "0") Integer pageNo, 
//                        @RequestParam(defaultValue = "10") Integer pageSize,
//                        @RequestParam(defaultValue = "timestamp") String sortBy) 
//    
//    {
//    	
//        List<TransactionPayMyBuddy> list = iTransactionService.getAllTransactions(pageNo, pageSize, sortBy);
// 
//        return new ResponseEntity<List<TransactionPayMyBuddy>>(list, new HttpHeaders(), HttpStatus.OK); 
//    }

//	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
//	Page<TransactionPayMyBuddy> transactionsPageable(Pageable pageable) {
//		
//		return transactionRepository.findAll(pageable);
//
//	}
	
	
	
	
//    @GetMapping
//    public String showPagination (HttpServletRequest request, Model model) {
//    int pageSize = 5;
//    int pageNo = 0;
//    
//    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//        pageNo = Integer.parseInt(request.getParameter("page")) - 1;
//    }
//
//    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//        pageSize = Integer.parseInt(request.getParameter("size"));
//    }
//
//    User user = iUserService.findUser();
//    model.addAttribute("sender1", user);
//    
//    Page<TransactionPayMyBuddy> page1 = iTransactionService.pagination(user, pageNo, pageSize);
//    List<TransactionPayMyBuddy> listTransactions = page1.getContent();
//    model.addAttribute("currentPage", pageNo);
//    model.addAttribute("totalPages", page1.getTotalPages());
//    model.addAttribute("totalItems", page1.getTotalElements());
//    model.addAttribute("transactions", listTransactions);
//    model.addAttribute("transaction", new TransactionDto());
//    return "transaction";
//    }

    //code java
//    @RequestMapping("/transfer/page/{pageNum}")
//    public String viewPage(Model model,
//            @PathVariable(name = "pageNum") int pageNum) {
//         
//        Page<TransactionPayMyBuddy> page = iTransactionService.listAll(pageNum);
//         
//        List<TransactionPayMyBuddy> listProducts = page.getContent();
//         
//        model.addAttribute("currentPage", pageNum);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("listProducts", listProducts);
//         
//        return "transfer";
//    }
//    @RequestMapping("/transfer")
//    public String viewHomePage(Model model) {
//        return viewPage(model, 1);
//    }
    
	
}


