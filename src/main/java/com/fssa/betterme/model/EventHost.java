package com.fssa.betterme.model;

/**
 * Represents a host of an event.
 */
public class EventHost {
	private int id ;
    private String hostName;
    private String contactNumber;
    private String email;

    /**
     * Constructs an EventHost object with all attributes.
     *
     * @param hostName       The name of the host.
     * @param contactNumber  The contact number of the host.
     * @param email          The email address of the host.
     */
    public EventHost(String hostName, String contactNumber, String email) {
        this.hostName = hostName;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    
    public EventHost(int hostId, String hostName, String mobileNumber, String email) {
    	this.id=hostId;
    	 this.hostName = hostName;
         this.contactNumber = mobileNumber;
         this.email = email;
	}



	/**
     * Returns a string representation of the host.
     *
     * @return The host name.
     */
    @Override
    public String toString() {
        return hostName;
    }

    
    /**
     * Gets the id of the host.
     *
     * @return The host id.
     */
    
	public int getId() {
		return id;
	}
    /**
     * Gets the name of the host.
     *
     * @return The host name.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Gets the contact number of the host.
     *
     * @return The host's contact number.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Gets the email address of the host.
     *
     * @return The host's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the name of the host.
     *
     * @param hostName The new host name.
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Sets the contact number of the host.
     *
     * @param contactNumber The new contact number.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Sets the email address of the host.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }





}
