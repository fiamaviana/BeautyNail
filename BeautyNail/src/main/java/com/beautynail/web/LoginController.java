package com.beautynail.web;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.AuthorityRepository;
import com.beautynail.repositories.UserRepository;
import com.beautynail.security.Authority;
import com.beautynail.services.UserService;

import javassist.NotFoundException;


@Controller

public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authoRepo;
	
	
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
			redirAttrs.addFlashAttribute("success", "Your account has been created! Please Log in.");
			return "redirect:/register";
		}
		//else redirect to the register page again
		redirAttrs.addFlashAttribute("error", "This email is already registered. Please Log in.");
		return "redirect:/register";
	}
	
	@GetMapping("/editAccount")
	public String editAccount(@AuthenticationPrincipal Users user)  {
		//loading the user from the databases
		Users loggedUser = userRepo.findByEmail(user.getEmail());
		//retrieve the page with the user id in the URL
		return "redirect:/editAccount/" + loggedUser.getId();
		
	}
	
	@GetMapping("/editAccount/{userId}")
	public String myAccount(@PathVariable Integer userId,ModelMap model, HttpServletResponse response) throws IOException {
		//load the user from databases
		Optional<Users> userOpt = userRepo.findById(userId);
		if(userOpt.isPresent()) {
			//put the user details in the model
			Users user = userOpt.get();
			model.put("user",user);	
		}else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "User " + userId + " not found");
			
		}
		return "editAccount";
	}
	
	//updating or deleting a booking
	@RequestMapping(method = RequestMethod.POST, value="/editAccount/{userId}")
	public String edit(@RequestParam(value="action",required=true)String action,
			@PathVariable Integer userId,@ModelAttribute  Users user,
			RedirectAttributes redirAttrs) throws IOException {
		
		if(action.equals("save")){
			//set authority
			userService.update(user);
			redirAttrs.addFlashAttribute("success", "Your details has been updated.");
		}
		if(action.equals("delete")) {
			userService.delete(user);
			//this message has to be set in another way
			redirAttrs.addFlashAttribute("error", "We are sorry to see you go. Your details has been removed from our data.");
			return "redirect:/register";
		}
		
		return "redirect:/editAccount/" + user.getId();
	}

	
}
