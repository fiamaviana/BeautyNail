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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;

@Controller
public class BookingContrroler {
	
	@Autowired
	private BookingRepository bookingRepo;

	
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
	
	//updating
	//populating details of the new booking and saving it in the databases
	@PostMapping("/booking/{bookingId}")
	public String saveBooking(@PathVariable Integer bookingId, Booking booking,@AuthenticationPrincipal Users user) {
		booking.setUser(user);
		booking = bookingRepo.save(booking);
		return "redirect:/dashboard";
	}
	
	//creating a new booking
	@PostMapping("/booking")
	public String createBooking(@AuthenticationPrincipal Users user) {
		Booking booking = new Booking();
		
		booking.setUser(user);

		booking = bookingRepo.save(booking);
				
		return "redirect:/booking/"+booking.getBookingId();
	}
	
	//deleting a new booking
	@DeleteMapping("/booking/{bookingId}")
	public String delete(@PathVariable Integer bookingId){
		//loading the booking from the databases
		Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
		if(bookingOpt.isPresent()) {
			bookingRepo.delete(bookingOpt.get());
		}
		return "redirect:/dashboard";
	}
}
