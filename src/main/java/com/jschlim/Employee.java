package com.jschlim;

public class Employee {

	private String name;
	private int id;
	
	public int getId() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	
	public void setId( int id ) {
		
		this.id = id;
	}
	
	public void setName( String name ) {
		
		this.name = name;
	}
	
	
	public String toString() {
		
		return "Employee: [name: " + name +
				", Id: " + id + "]";
	}
}
