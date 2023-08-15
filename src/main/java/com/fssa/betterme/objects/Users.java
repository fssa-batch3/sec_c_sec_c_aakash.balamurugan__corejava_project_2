package com.fssa.betterme.objects;


public class Users {

    private String username;
    private String email;
    private String password;
    private Gender gender;
    
    
	public Users( String username, String email, String password, Gender gender) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		
	}
 
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}



}
