package com.fssa.betterme.model;

/**
 * Represents a host of an event.
 */
public class Trainner {
	private int id ;
    private String trainerName;
    private String contactNumber;
    private String email;

    /**
     * Constructs an EventHost object with all attributes.
     *
     * @param hostName       The name of the host.
     * @param contactNumber  The contact number of the host.
     * @param email          The email address of the host.
     */
    public Trainner(String hostName, String contactNumber, String email) {
        this.trainerName = hostName;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    
    public Trainner(int hostId, String hostName, String mobileNumber, String email) {
    	this.id=hostId;
    	 this.trainerName = hostName;
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
   		return "EventHost [id=" + id + ", hostName=" + trainerName + ", contactNumber=" + contactNumber + ", email="
   				+ email + "]";
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
    public String getTrainerName() {
        return trainerName;
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
     * @param trainerName The new host name.
     */
    public void setId(int hostId) {
        this.id = hostId;
    }
    
    /**
     * Sets the name of the host.
     *
     * @param hostName The new host name.
     */
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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
