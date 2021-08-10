package com.beautynail.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.Optional;

import com.beautynail.domain.Booking;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingRepositoryTest {
	@Autowired
	private BookingRepository repo;
	
	
	@Test
	public void testFindBooking() {
		Booking myBooking = new Booking(1,"15/02/1907","15:00",null,null);
		Optional<Booking> bookings = repo.findBookingByDateAndTime(myBooking);
		//assertNotEquals(bookings.isPresent(), false);
	}

}
