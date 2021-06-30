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
	private String date;
	private String time;
	private Users user;
	private Set<Manicure> manicures = new HashSet<>();

	
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
	
	@OneToMany(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY, mappedBy="booking")
	public Set<Manicure> getManicures() {
		return manicures;
	}
	public void setManicures(Set<Manicure> manicures) {
		this.manicures = manicures;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", date=" + date + ", time=" + time + ", user=" + user
				+ ", manicures=" + manicures + "]";
	}
	
	
	
}
