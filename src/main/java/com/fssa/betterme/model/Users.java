package com.fssa.betterme.model;


public class Users {

	private int id;
	private String username;
    private String email;
    private String password;
    private long phoneNumber;
    private Gender gender;
    private boolean isActive;
    
    
	public Users(String username, String email, String password, long phoneNumber, Gender gender) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
    public Users(int id, String username, String email, String password, long phoneNumber, Gender gender,
			boolean isActive) {
    	this( username,  email,  password,  phoneNumber,  gender);
		this.id = id;
		this.isActive = isActive;
		
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}  
    
    


}