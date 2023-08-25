package com.fssa.betterme.model;


import java.time.*;

/**
 * Represents an event.
 */
public class Events {
	
	private int id;
    private String eventName;
    private String eventDescription;
    private String eventAddress;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private double price;
    private String imageUrl;
    private EventHost host;
    private boolean isActive;
    

    /**
     * Constructs an Events object with all attributes.
     *
     * @param eventName        The name of the event.
     * @param eventName        The name of the event.
     * @param eventDescription The description of the event.
     * @param eventAddress     The address of the event.
     * @param eventDate        The date of the event.
     * @param eventTime        The time of the event.
     * @param price            The price of the event.
     */
    
    public Events(int eventid,String eventName, String eventDescription,String imageURL, String eventAddress,
                  LocalDate eventDate, LocalTime eventTime, double price, boolean isActive) {
    	this.id=eventid;
        this.eventName = eventName; 
        this.eventDescription = eventDescription;
        this.eventAddress = eventAddress;
        this.imageUrl = imageURL;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.price = price;
        this.isActive = isActive;
      
    }

    /**
     * Constructs an Events object with limited attributes.
     *
     * @param eventName        The name of the event.
     * @param eventDescription The description of the event.
     * @param eventAddress     The address of the event.
     * @param eventDate        The date of the event.
     * @param eventTime        The time of the event.
     * @param price            The price of the event.
     */
    
    public Events(String eventName, String eventDescription, String eventAddress,String imageURL,
                  LocalDate eventDate, LocalTime eventTime, double price,EventHost host) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventAddress = eventAddress;
        this.imageUrl = imageURL;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.price = price;
        this.host = host;
       
    }
    


    /**
     * Returns a string representation of the event.
     *
     * @return The event name.
     */
    @Override
    public String toString() {
        return "event Name: "+getEventName()+" price: "+getPrice()+" Date: "+ getEventDate()+" Time: "+getEventTime() ;
    }

    /**
     * Gets the Id of the event.
     *
     * @return The event Id.
     */
    
	public int getId() {
		return id;
	}

    /**
     * Gets the name of the event.
     *
     * @return The event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets the description of the event.
     *
     * @return The event description.
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Gets the address of the event.
     *
     * @return The event address.
     */
    public String getEventAddress() {
        return eventAddress;
    }

    /**
     * Gets the date of the event.
     *
     * @return The event date.
     */
    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * Gets the time of the event.
     *
     * @return The event time.
     */
    public LocalTime getEventTime() {
        return eventTime;
    }

    /**
     * Gets the price of the event.
     *
     * @return The event price.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the address of the event.
     *
     * @return The event address.
     */
    public String getImageURL() {
        return imageUrl;
    }
    
    /**
     * Gets the host of the event.
     *
     * @return The event host.
     */
    
    
    public EventHost getHost() {
        return host;
    }
    
    /**
     * Gets the active status of the event.
     *
     * @return The event status of active.
     */
    
    
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName The new event name.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Sets the description of the event.
     *
     * @param eventDescription The new event description.
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Sets the address of the event.
     *
     * @param eventAddress The new event address.
     */
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    /**
     * Sets the date of the event.
     *
     * @param eventDate The new event date.
     */
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Sets the time of the event.
     *
     * @param eventTime The new event time.
     */
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Sets the price of the event.
     *
     * @param price The new event price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    
    /**
     * Sets the imageUrl of the event.
     *
     * @param imageUrl The new event imageUrl.
     */
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    
    /**
     * Sets the host of the event.
     *
     * @param host The new event host.
     */
    public void setHost(EventHost host) {
        this.host = host;
    }
    
    /**
     * Sets the active status of the event.
     *
     * @param  event activity status.
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }



	

}
