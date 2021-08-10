package com.beautynail.web;

import java.text.ParseException;
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
import com.beautynail.services.BookingService;
import com.beautynail.services.UserDetailsServiceImpl;

@Controller
public class DashboardController {
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal Users user, ModelMap model, HttpServletRequest request) throws ParseException{
		List<Booking> booking;
		
		booking = bookingRepo.findAll();
		//handling an error, deleting a booking in case a it was created without a date or time or user
		for(Booking thisBook : booking) {
			if(thisBook.getDate() == null || thisBook.getUser() == null || thisBook.getTime() == null) {
				bookingRepo.delete(thisBook);
			}
		}
		//if the user is admin it will show all bookings
		if (request.isUserInRole("ROLE_ADMIN")) {
			booking = bookingRepo.findAllByOrderByDateAsc();
	    }
		else {
			//if is a customer it will show only their bookings
			booking = bookingRepo.findByUser(user);
		}
		model.put("user", user);	
		model.put("booking", booking);
		return "dashboard";
	}
	
	
}
