package com.swathi.mysqljdbc.model;

public class User {

	private int id;
	private String userName;
	private String city;
	
	public User(int id, String userName, String city) {
		super();
		this.id = id;
		this.userName = userName;
		this.city = city;
	}

	public User(String userName, String city) {
		super();
		this.userName = userName;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getCity() {
		return city;
	}
	
	
}
