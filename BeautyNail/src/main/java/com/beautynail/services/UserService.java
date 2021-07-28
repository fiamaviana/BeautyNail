package com.beautynail.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beautynail.domain.Users;
import com.beautynail.repositories.AuthorityRepository;
import com.beautynail.repositories.UserRepository;
import com.beautynail.security.Authority;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private AuthorityRepository authRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users save(Users user) {
		//set encoded password to the data
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		//set roles to a user
		Authority authority= new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user); 
		
		user.getAuthorities().add(authority);
		
		
		return userRepo.save(user);
	}
	
	public Users update(Users user) {
		//set encoded password to the data
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		return userRepo.save(user);
	}
	
	public void delete(Users user) {
		//delete authority first
		Authority auth = authRepo.findByUserId(user.getId());
		System.out.println("the authority was found, id: " + auth.getId());
		 
		//for some reason it is not deleting this data, and I need to delete first the authority to delete the user
		user.getAuthorities().clear();
		authRepo.deleteById(auth.getId());
		
		
		//then delete user
		userRepo.deleteById(user.getId());
	}
	
}
