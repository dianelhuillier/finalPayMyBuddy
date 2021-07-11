//package com.openclassroom.payMyBuddy.service;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.openclassroom.payMyBuddy.model.UserModel;
//import com.openclassroom.payMyBuddy.repository.UserRepository;
//
//import org.apache.commons.validator.routines.EmailValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.ValidationUtils;
//
//public class UserServiceValidator implements Validator{
//
//	  private EmailValidator emailValidator = EmailValidator.getInstance();
//	  
//	    @Autowired
//	    private UserRepository userRepository;
//	 
//	    @Override
//	    public boolean supports(Class<?> clazz) {
//	        return clazz == UserService.class;
//	    }
//	 
//	    @Override
//	    public void validate(Object target, Errors errors) {
//	        UserService userServiceForm = (UserService) target;
//	 
//	        // Check the fields of AppUserForm.
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
//	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
//
//	 
//	        if (!this.emailValidator.isValid(userServiceForm.getEmail(email))) {
//	            // Invalid email.
//	            errors.rejectValue("email", "Pattern.appUserForm.email");
//	        } else if (userServiceForm.getId(null) == null) {
//	        	UserModel dbUser = userRepository.findByEmail(userServiceForm.getEmail());
//	            if (dbUser != null) {
//	                errors.rejectValue("email", "Duplicate.UserForm.email");
//	            }
//	        }
//	 
//	        if (!errors.hasFieldErrors("userName")) {
//	            UserService dbUser = userRepository.findByLastName(userServiceForm.getLastName());
//	            if (dbUser != null) {
//	                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
//	            }
//	        }
//	 
//	        if (!errors.hasErrors()) {
//	            if (!userServiceForm.getConfirmPassword().equals(userServiceForm.getPassword())) {
//	                errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
//	            }
//	        }
//	    }
//
//}
