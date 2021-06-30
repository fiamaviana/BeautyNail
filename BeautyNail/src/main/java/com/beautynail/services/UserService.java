package com.beautynail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beautynail.domain.Users;
import com.beautynail.repositories.UserRepository;
import com.beautynail.security.Authority;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users save(Users user) {
		//send encoded password to the data
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		//set roles to a user
		Authority authority= new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user); 
		
		user.getAuthorities().add(authority);
		
		
		return userRepo.save(user);
	}
}
