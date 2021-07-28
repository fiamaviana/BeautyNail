package com.beautynail.services;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;

@DataJpaTest
class BookingServiceTest {
	
	@Autowired
	private BookingRepository bookingRepo;

	@Test
	public void saving_booking_test() {
		Users user= new Users();
		Booking booking = new Booking(null,"nailDesigner","07/27/2021","14:00",user);
		Booking saved = bookingRepo.save(booking);
		
		assertNotNull(saved);
		
	}

}
