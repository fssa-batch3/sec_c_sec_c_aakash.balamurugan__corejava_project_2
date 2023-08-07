package com.fssa.betterme.objects;


import java.time.*;

public class Events extends EventHost{
	private String EventName;


	private String EventDescription;
	private String EventAddress;
	private LocalDate EventDate;
	private LocalTime EventTime;
	private double Price;
	private EventHost Host;
	public Events( String eventName,
			String eventDescription, String eventAddress, LocalDate eventDate, LocalTime eventTime, double price,
			EventHost host) {
		super(host.getHostName(), host.getContactNumber(), host.getEmail());
		this.EventName = eventName;
		this.EventDescription = eventDescription;
		this.EventAddress = eventAddress;
		this.EventDate = eventDate;
		this.EventTime = eventTime;
		this.Price = price;
		this.Host = host;
	}

	public String getEventName() {
		return EventName;
	}

	public String getEventDescription() {
		return EventDescription;
	}

	public String getEventAddress() {
		return EventAddress;
	}

	public LocalDate getEventDate() {
		return EventDate;
	}

	public LocalTime getEventTime() {
		return EventTime;
	}

	public double getPrice() {
		return Price;
	}

	public EventHost getHost() {
		return Host;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public void setEventDescription(String eventDescription) {
		EventDescription = eventDescription;
	}

	public void setEventAddress(String eventAddress) {
		EventAddress = eventAddress;
	}

	public void setEventDate(LocalDate eventDate) {
		EventDate = eventDate;
	}

	public void setEventTime(LocalTime eventTime) {
		EventTime = eventTime;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public void setHost(EventHost host) {
		Host = host;
	}	
	
	

}
