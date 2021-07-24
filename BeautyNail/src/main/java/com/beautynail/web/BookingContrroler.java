package com.beautynail.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
				
		}
		
		else {//handling exception in case the booking does not exist
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
			@PathVariable Integer bookingId,Booking booking,@AuthenticationPrincipal Users user,
			RedirectAttributes redirAttrs) throws IOException {
		
		
		if(action.equals("save")){
			//check if already exists a booking with the same date and time
			Optional<Booking> optBooking = bookingRepo.findBooking(booking);
			if(optBooking.isPresent()) {
				redirAttrs.addFlashAttribute("error", "Date and Time is not available, please choose another.");
				//response.sendError(HttpStatus.NOT_FOUND.value(),"Date and Time not available " );
				return "redirect:/booking/" +booking.getBookingId();
			}else {
				booking.setUser(user);
				redirAttrs.addFlashAttribute("success", "Thank you for chosen Beauty Nail! ");
				booking = bookingRepo.save(booking);
				return "redirect:/booking/" +booking.getBookingId();
			}
		}
		if(action.equals("delete")) {
			bookingRepo.deleteById(bookingId);	
		}
		
		if(action.equals("checkTimeAvailable")) {
			//this will be checked when the user select a date and it will display only time available for that date chosen
			List<Booking> bookings  = bookingRepo.findBookingByDate(booking);
			ArrayList<String> times = new ArrayList<String>();
			
		    if(bookings != null) {
		    	String date = booking.getDate();
		    	for(Booking book : bookings) {
		    		date = book.getDate();
		    		times.add(book.getTime());
		    	}
		    	if(times.isEmpty()) {
		    		redirAttrs.addFlashAttribute("success", "You can chose any time in " + date  + ".");
		    		
		    	} else {
		    		redirAttrs.addFlashAttribute("error", "Not available in " +  date  + times);
		    	}
		    	
		    	
		    	return "redirect:/booking/" +booking.getBookingId();
		}
	
		
	}
		return "redirect:/dashboard"; 
		
	}
	
}
	

	

