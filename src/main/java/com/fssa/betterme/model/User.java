package com.fssa.betterme.model;


public class User {

	private int id;
	private String username;
    private String email;
    private String password;
    private long phoneNumber;
    private Gender gender;

    
    

	public User(String username, String email, String password, long phoneNumber, Gender gender) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
    public User(int id, String username, String email, String password, long phoneNumber, Gender gender) {
    	this( username,  email,  password,  phoneNumber,  gender);
		this.id = id;
		
		
	}
    



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		
		
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

    
    


}