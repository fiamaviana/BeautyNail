package com.beautynail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;

//this repository enables all CRUD operations 
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	//select * from booking where user = :user
	List<Booking> findByUser(Users user);

}
