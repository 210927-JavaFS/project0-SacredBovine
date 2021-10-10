package com.revature.models;

public class Name {

	private int id;
	private String firstName;
	private String lastName;

// Constructors	
	public Name() {
		super();
	}
	public Name(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
// Getters	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getID() {
		return id;
	}

// Setters	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setId(int input) {
		this.id = input;
	}

// Overrides
	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
	
}
