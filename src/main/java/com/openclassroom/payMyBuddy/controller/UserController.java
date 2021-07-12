package com.openclassroom.payMyBuddy.controller;

import java.util.Date;
import com.openclassroom.payMyBuddy.service.EmailValidation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openclassroom.payMyBuddy.model.MessageModel;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.model.dto.UserDto;
import com.openclassroom.payMyBuddy.repository.UserRepository;
import com.openclassroom.payMyBuddy.service.UserService;

@Controller
public class UserController {


	@Autowired
	private UserService userService;	

	@GetMapping("/login")
	public String showLogin (Model model) {
		return "login";
	}

	@GetMapping("/registration")
	public String showRegistration (Model model) {
		User user = new User();
		model.addAttribute("newUser", user);
		return "registration";
	}

	@PostMapping("/registration")
	public String processRegister(@ModelAttribute User newUser, Model model) {

		final User userExists = userService.findUserByEmail(newUser.getEmail());

		if (EmailValidation.isValidEmail(newUser.getEmail())) {

			if (userExists != null) {
				model.addAttribute("messageError", "There is already a user registered with the email provided");
				return "registration";

			} else {
				userService.saveUser(newUser);
				User user = new User();
				model.addAttribute("newUser", user);
				model.addAttribute("messageError", "User has been registered successfully");

				return "registration";
			}	     
		}else {
			model.addAttribute("messageError", "Email address is incorrect");
			return "registration";
		}
	}

	@ModelAttribute("profil")
	public UserDto userDto() {
		return new UserDto();
	}


}
