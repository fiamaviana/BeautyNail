package com.beautynail.domain;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Booking {
	private Integer bookingId;
	private String date;
	private String time;
	private Users user;
	private Manicure manicure;
	

	public Booking() {
		super();
	}
	public Booking(Integer bookingId, String date, String time, Users user, Manicure manicure) {
		super();
		this.bookingId = bookingId;
		this.date = date;
		this.time = time;
		this.user = user;
		this.manicure = manicure;
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
	
	@ManyToOne
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	@ManyToOne
	public Manicure getManicure() {
		return manicure;
	}
	public void setManicure(Manicure manicure) {
		this.manicure = manicure;
	}

	
}
