package com.beautynail.services;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.repositories.BookingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingServiceTest {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Test
	public void saving_booking_test() {
		Booking booking = new Booking(1,null,"14:00",null,null);
		Booking saved = bookingRepo.save(booking);
		
		//this test will fail if I delete the booking because the booking will be empty,and it is fine.
		//bookingRepo.deleteById(1);
		assertNotEquals(saved,null);
		
	}
	

}
