package com.fssa.betterme.objects;

public class  EventHost {

	protected String hostName;
	protected String contactNumber;
//	protected Rating review ;
	protected String email;
	
	
	public EventHost(String hostName, String contactNumber, Rating review, String email) {
		
		this.hostName = hostName;
		this.contactNumber = contactNumber;
//		this.review = review;
		this.email = email;
	}

	
	public EventHost(String hostName, String contactNumber, String email) {
		
		this.hostName = hostName;
		this.contactNumber = contactNumber;
//		this.review = review;
		this.email = email;
	}
	public String getHostName() {
		return hostName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
//	public Rating getReview() {
//		return review;
//	}
	public String getEmail() {
		return email;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
//	public void setReview(Rating review) {
//		this.review = review;
//	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
