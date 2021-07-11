package com.beautynail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.beautynail.domain.Booking;
import com.beautynail.repositories.BookingRepository;
@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepo;
	
	public void delete(@PathVariable Integer bookingId,Booking booking){
		//bookingRepo.delete(booking);
		bookingRepo.deleteById(bookingId);
		
	}
}
