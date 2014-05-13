package com.tyler.webapp;

public class Student {
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String speak(){
		return "I am " + this.name + " and my student number is: " + this.id;
	}
}
