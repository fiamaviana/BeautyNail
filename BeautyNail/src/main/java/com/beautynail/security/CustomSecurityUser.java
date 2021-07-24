package com.beautynail.security;

import java.util.Set;


import org.springframework.security.core.userdetails.UserDetails;

import com.beautynail.domain.Users;

public class CustomSecurityUser extends Users implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomSecurityUser() {}

	public CustomSecurityUser(Users user) {
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
	}
	
	
	@Override
	public Set<Authority> getAuthorities() {
		//used super instead of this, to avoid call the method recursively 
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		//used super instead of this, to avoid call the method recursively 
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		//used super instead of this, to avoid call the method recursively 
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
