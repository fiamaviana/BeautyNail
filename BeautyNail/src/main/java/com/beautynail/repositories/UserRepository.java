package com.beautynail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beautynail.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByEmail(String username);

	
	
}
