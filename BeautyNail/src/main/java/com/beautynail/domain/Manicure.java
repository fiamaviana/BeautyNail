package com.beautynail.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manicure {
	private Integer manicureID;
	private String type;
	private double price;
	
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
	
	

}
