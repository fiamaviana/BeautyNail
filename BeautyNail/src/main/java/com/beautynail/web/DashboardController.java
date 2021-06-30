package com.beautynail.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;

@Controller
public class DashboardController {
	@Autowired
	private BookingRepository bookingRepo;
	
	@GetMapping("/")
	public String rootView() {
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal Users user, ModelMap model) {
		List<Booking> booking = bookingRepo.findByUser(user);
		model.put("booking", booking);
		return "dashboard";
	}
	
}
