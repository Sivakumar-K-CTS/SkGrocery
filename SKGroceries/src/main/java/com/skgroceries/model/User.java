package com.skgroceries.model;

public class User {
	
	private Integer userID;
	private String name;
	private String username;  //must be unique
	private String password;
	private String location;
	private long mobileNumber;
	
	public User() {
		super();
	}

	public User(int userID,String name, String username, String password, String location, long mobileNumber) {
		super();
		this.userID = userID;
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
		this.mobileNumber = mobileNumber;
	}
	public User(String name, String username, String password, String location, long mobileNumber) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
		this.mobileNumber = mobileNumber;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "User [userID="+userID+",name=" + name + ", username=" + username + ", password=" + password + ", location=" + location
				+ ", mobileNumber=" + mobileNumber + "]";
	}
	
}
