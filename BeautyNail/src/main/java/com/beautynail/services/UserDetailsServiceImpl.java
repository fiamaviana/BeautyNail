package com.beautynail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beautynail.domain.Users;
import com.beautynail.repositories.UserRepository;
import com.beautynail.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	//check if the user exists in the database and check if the password matches
	@Autowired
	private UserRepository userRepo;
	
	
	//this method should checks if the user is in the database and check the password if its match
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByEmail(username);
		if(user == null)
			throw new UsernameNotFoundException("Invalid Username and password");
		return new CustomSecurityUser(user);
	}

}
