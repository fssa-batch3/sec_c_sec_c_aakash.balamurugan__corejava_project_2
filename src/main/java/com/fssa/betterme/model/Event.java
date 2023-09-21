package com.fssa.betterme.model;


import java.time.*;

import javax.validation.constraints.NotNull;

/**
 * Represents an event.
 */
public class Event {
	
	private int id;
	
	@NotNull(message = "event name should not be null")
    private String eventName;
    private String EventAbout;
    private String eventDescription;
    private String eventAddress;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private double price;
    private String imageUrl;
    private boolean isActive;
    private Trainner trainner;
    
    
    
	public Event(int id, String eventName, String eventAbout, String eventDescription, String eventAddress,
			LocalDate eventDate, LocalTime eventTime, double price, String imageUrl, boolean isActive) {
		this.id = id;
		this.eventName = eventName;
		this.EventAbout = eventAbout;
		this.eventDescription = eventDescription;
		this.eventAddress = eventAddress;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.price = price;
		this.imageUrl = imageUrl;
		this.isActive = isActive;
		
	}
	
	public Event( String eventName, String eventAbout, String eventDescription, String eventAddress,
			LocalDate eventDate, LocalTime eventTime, double price, String imageUrl, 
			Trainner trainner) {
		
		this.eventName = eventName;
		this.EventAbout = eventAbout;
		this.eventDescription = eventDescription;
		this.eventAddress = eventAddress;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.price = price;
		this.imageUrl = imageUrl;
		this.trainner = trainner;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventAbout() {
		return EventAbout;
	}
	public void setEventAbout(String eventAbout) {
		EventAbout = eventAbout;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventAddress() {
		return eventAddress;
	}
	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	public LocalTime getEventTime() {
		return eventTime;
	}
	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Trainner getTrainner() {
		return trainner;
	}
	public void setTrainner(Trainner trainner) {
		this.trainner = trainner;
	}
    
    
}

	
