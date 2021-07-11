package com.beautynail.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;
import com.beautynail.services.BookingService;


@Controller
public class BookingContrroler {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private BookingService bookingServ;


	@GetMapping("/booking/{bookingId}")
	public String getBooking(@PathVariable Integer bookingId, ModelMap model, HttpServletResponse response) throws IOException{
		//loading the booking from the databases
		Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
		//put the booking into the model 
		if(bookingOpt.isPresent()) {
			Booking booking = bookingOpt.get();
			model.put("booking", booking); //this enables to take the booking id and populate the field id in the booking.html file 
		}else {//handling exception in case the booking does not exist
			response.sendError(HttpStatus.NOT_FOUND.value(),"Booking with id " + bookingId + " not found");
			return "booking";
		}
		return "booking";
	}
	
	
	//creating a new booking
	@PostMapping("/booking")
	public String createBooking(@AuthenticationPrincipal Users user) {
		Booking booking = new Booking();

		booking = bookingRepo.save(booking);
				
		return "redirect:/booking/"+booking.getBookingId();
	}
	

	//updating or deleting a booking
	@RequestMapping(method = RequestMethod.POST, value="/booking/{bookingId}")
	public String edit(@ModelAttribute ModelMap model,
			@RequestParam(value="action",required=true)String action,
			@PathVariable Integer bookingId,Booking booking,@AuthenticationPrincipal Users user) {
		if(action.equals("save")){
			booking.setUser(user);
			booking = bookingRepo.save(booking);
		}
		if(action.equals("delete")) {
			bookingRepo.deleteById(bookingId);	
		}
				
		return "redirect:/dashboard"; 
	}
	
	
}
