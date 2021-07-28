package com.beautynail.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Booking {
	private Integer bookingId;
	private String type;
	private String date;
	private String time;
	private Users user;

	
	public Booking() {
		
	}
	public Booking(Integer bookingId, String type, String date, String time, Users user) {
		super();
		this.bookingId = bookingId;
		this.type = type;
		this.date = date;
		this.time = time;
		this.user = user;
	}
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@ManyToOne
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	

	
}
