package com.openclassroom.payMyBuddy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassroom.payMyBuddy.model.MessageModel;
import com.openclassroom.payMyBuddy.model.User;
import com.openclassroom.payMyBuddy.service.IMessageService;

@Controller
public class MessageController {

	@Autowired
	IMessageService iMessageService;

	
	@GetMapping ("/addmsg")
	public String showMessage(Model model) {
		MessageModel message = new MessageModel();
		model.addAttribute("newMessage",  message);
		return "MesgPage";
	}

	@PostMapping ("/addmsg")
	public String postMessage(@ModelAttribute MessageModel newMessage) {
		String dateTime = new Date().toString();
		newMessage.setTimeStamp(dateTime);
		iMessageService.savedMessage(newMessage);
		System.out.println(newMessage.toString());
		return "redirect:listmsg";
	}



	@GetMapping ("/listmsg")
	public String  listMessages (Model model) {
		List<MessageModel> listmsg = iMessageService.listAllMessages();
		model.addAttribute("msgList", listmsg);
		return "ListMessages";
	}
	
	@GetMapping("/detail")
	public String detailMessages (@RequestParam(value = "id") String id, Model model) {
		Long idMessage = Long.parseLong(id);
		MessageModel message = iMessageService.getMessageById(idMessage);
		model.addAttribute("msg", message);
		return "LectureMessage";
		}
	
	@GetMapping("/delete")
	public String deleteMessages (@RequestParam(value = "id") String id, Model model) {
		Long idMessage = Long.parseLong(id);
		iMessageService.deleteMessageById(idMessage);
		return "redirect:listmsg";
		}
	
	@GetMapping("/profile")
		public String showProfile (Model Model) {
		return "profile";
	}
	

	
	@GetMapping("/index")
	public String showIndex (Model Model) {
	return "index";
	}
	





	
	


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/access-denied";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}