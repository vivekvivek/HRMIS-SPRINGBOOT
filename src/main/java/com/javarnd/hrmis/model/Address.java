package com.javarnd.hrmis.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Address {
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String street;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
		
}