package com.beautynail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Users;
import com.beautynail.security.Authority;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
	//select * from booking where userId = :userId
	Authority findByUserId(Integer id);
}
