package com.fssa.betterme.objects;

public class  EventHost {

	protected String hostName;
	protected String contactNumber;

	protected String email;
	
	


	
	public EventHost(String hostName, String contactNumber, String email) {
		
		this.hostName = hostName;
		this.contactNumber = contactNumber;

		this.email = email;
	}
	public String getHostName() {
		return hostName;
	}
	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmail() {
		return email;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
