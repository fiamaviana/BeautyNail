package com.beautynail.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;

//this repository enables all CRUD operations 
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	//select * from booking where user = :user
	List<Booking> findByUser(Users user);
	
	
	//select * from booking where date =: date and time =: time
	@Query("SELECT booking FROM Booking booking WHERE booking.date = :#{#req.date} and booking.time =:#{#req.time}")
	Optional<Booking> findBookingByDateAndTime(@Param("req")Booking req);


	//select * from booking where date =: date
	@Query("SELECT booking FROM Booking booking WHERE booking.date = :#{#req.date}")
	List<Booking> findBookingByDate(@Param("req")Booking req);

	//select * from booking where user = :null
	List<Booking> findByUserIsNull();


	

}
