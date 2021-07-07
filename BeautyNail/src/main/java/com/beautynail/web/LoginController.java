package com.beautynail.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.beautynail.domain.Users;
import com.beautynail.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
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
	public String registerPost (@ModelAttribute Users user) {
		
		userService.save(user);
		//when making a post, return a redirect page to avoid sending duplicate data
		return "login";
	}
	@GetMapping("/manicure")
	public String manicure() {
		return "manicure";
	}
}
