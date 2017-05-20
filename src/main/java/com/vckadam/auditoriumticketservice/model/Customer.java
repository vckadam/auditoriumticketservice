package com.vckadam.auditoriumticketservice.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private String firstName, lastName, email;
	private long phoneNum;
	private Set<String> conformations;
	
	public Customer(String email) {
		this.email = email;
		conformations = new HashSet<String>();
	}
	
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Set<String> getConformations() {
		return conformations;
	}
	public void addConformations(String conformation) {
		this.conformations.add(conformation);
	}
}
