package com.beautynail.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beautynail.domain.Users;
import com.beautynail.repositories.UserRepository;
import com.beautynail.services.UserService;


@Controller

public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		model.put("user", new Users());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost (@ModelAttribute Users user,RedirectAttributes redirAttrs) {
		//check if username is alredy registered
		Users users = userRepo.findByEmail(user.getEmail());
		//if the username is not taken, the save the new user 
		if(users == null) {
			userService.save(user);
			redirAttrs.addFlashAttribute("success", "Thank you for chosen Beauty Nail! Please Log in.");
			return "redirect:/register";
		}
		//else redirect to the register page again
		redirAttrs.addFlashAttribute("error", "This email is already registered. Please Log in.");
		return "redirect:/register";
	}
	
	
	
	
}
