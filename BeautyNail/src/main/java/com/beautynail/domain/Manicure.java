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
public class Manicure {
	private Integer manicureID;
	private String type;
	private String description;
	private double price;
	private Set<Booking> booking = new HashSet<>();
	
	public Manicure() {
		super();
	}
	public Manicure(Integer manicureID, String type, String description, double price) {
		super();
		this.manicureID = manicureID;
		this.type = type;
		this.description = description;
		this.price = price;
	}
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getManicureID() {
		return manicureID;
	}
	public void setManicureID(Integer manicureID) {
		this.manicureID = manicureID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="manicure")
	public Set<Booking> getBooking() {
		return booking;
	}
	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}
	
	
		

}
