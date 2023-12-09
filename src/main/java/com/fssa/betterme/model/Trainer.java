package com.fssa.betterme.model;



public class Trainer {
    private int id;
    private String trainerName;
    private String imageLink;
    private String email;
    private String loginPass;
    private String occupation;
    private String content;
    private String startTime;
    private String endTime;
    private String education;
    private String exprience;
    
    
    
    public Trainer(String trainerName, String imageLink, String email, String loginPass, String occupation,
			String content, String startTime, String endTime, String education, String exprience) {
	
		this.trainerName = trainerName;
		this.imageLink = imageLink;
		this.email = email;
		this.loginPass = loginPass;
		this.occupation = occupation;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.education = education;
		this.exprience = exprience;
	}
    
    public Trainer(int id,String trainerName, String imageLink, String email, String occupation,
			String content, String startTime, String endTime, String education, String exprience) {
    	this(trainerName, imageLink, email, null, occupation, content, startTime, endTime, education, exprience);
    	this.id = id;

	}
	public Trainer(int mId, String mName, String mImage, String mOccupation, String mEmail) {
		this.id = mId;
		this.trainerName = mName;
		this.imageLink = mImage;
		this.occupation = mOccupation;
		this.email = mEmail;
		
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String login) {
		this.email = login;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExprience() {
		return exprience;
	}
	public void setExprience(String exprience) {
		this.exprience = exprience;
	}
	
    
    
}