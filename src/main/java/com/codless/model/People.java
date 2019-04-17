package com.codless.model;

/**
 * 
 * Simple entity to demonstrate the conversion in request
 *  
 * */
public class People {

	private String name;
	private String lastName;
	
	public People() {} 
	
	public People(String name, String lastName) {

		this.name = name;
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", lastName=" + lastName + "]";
	}
}
