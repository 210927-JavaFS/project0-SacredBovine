package com.revature.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Address {
	
	private int addressID;
	private String streetNumber;
	private String streetName;
	private String city;
	private String region;
	private String zipcode;
	private String country;
	
// Constructors
	public Address() {
		super();
	}
	public Address(int addressID, String streetNumber, String streetName, String city, String region, String zipcode,
			String country) {
		super();
		this.addressID = addressID;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.region = region;
		this.zipcode = zipcode;
		this.country = country;
	}
	public Address(String streetNumber, String streetName, String city, String region, String zipcode, String country) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.region = region;
		this.zipcode = zipcode;
		this.country = country;
	}
// Getters	
	public int getAddressID() {
		return addressID;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public String getCity() {
		return city;
	}
	public String getRegion() {
		return region;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getCountry() {
		return country;
	}
	
// Setters	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public void setCountry(String country) {
		this.country = country;
	}

// Overrides	
	@Override
	public String toString() {
		String address = " " + streetNumber + " " + streetName + " \n " + city + " ," + region + " \n " + country;
		return address;
	}

}
