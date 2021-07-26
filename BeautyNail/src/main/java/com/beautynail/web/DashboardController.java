package com.beautynail.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;
import com.beautynail.services.UserDetailsServiceImpl;

@Controller
public class DashboardController {
	@Autowired
	private BookingRepository bookingRepo;

	
	
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal Users user, ModelMap model, HttpServletRequest request){
		List<Booking> booking;
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			//if the admin is logged in it will show all bookings
			booking = bookingRepo.findAll();
	    }
		else {
			//if is a user it will show only their bookings
			booking = bookingRepo.findByUser(user);
		}
		
		//handling an error, in case a booking is created without a user but I need to find another way to improve this
		for(Booking thisBook : booking) {
			if(thisBook.getUser() == null) {
				bookingRepo.delete(thisBook);
			}
		}
		
		model.put("booking", booking);
		return "dashboard";
	}
	
	
}
