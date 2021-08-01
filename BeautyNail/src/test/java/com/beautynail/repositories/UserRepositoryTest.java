package com.beautynail.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beautynail.domain.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testFindUserByUsername() {
		String email = "fiamamviana@gmail.com";
		Users username = repo.findByEmail(email);
		
		assertEquals(email,username.getEmail());
		
	}

}
