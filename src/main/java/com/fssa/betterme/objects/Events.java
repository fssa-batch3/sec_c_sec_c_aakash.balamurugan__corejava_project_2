package com.fssa.betterme.objects;


import java.sql.Date;
import java.sql.Time;
import java.time.*;

public class Events {
	private String eventName;


	private String eventDescription;
	private String eventAddress;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private double price;
	private EventHost host;

	public Events( String eventName,
			String eventDescription, String eventAddress, LocalDate eventDate, LocalTime eventTime, double price,
			EventHost host) {
		
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventAddress = eventAddress;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.price = price;
		this.host = host;
	}

	public Events(int eventId, String eventName, Date eventDate, Time eventTime, double price) {
		this.eventName = eventName;
		
		this.eventDate = eventDate.toLocalDate();
		this.eventTime = eventTime.toLocalTime();
		this.price = price;
		
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public double getPrice() {
		return price;
	}

	public EventHost getHost() {
		return host;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setHost(EventHost host) {
		this.host = host;
	}	
	
	

}
