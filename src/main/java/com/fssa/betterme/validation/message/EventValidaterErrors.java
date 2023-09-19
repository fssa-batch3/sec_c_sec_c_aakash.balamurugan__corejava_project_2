package com.fssa.betterme.validation.message;

/**
 * Constants for error messages related to event validation.
 */
public final class EventValidaterErrors {
    
    // Error messages for event validation
    public static final String EVENT_NULL_ERROR = "Event cannot be null";
    public static final String EVENTNAME_NULL_ERROR = "Event Name cannot be null";
    public static final String EVENTDESCRIPTION_NULL_ERROR = "Event Description cannot be null";
    public static final String EVENTABOUT_NULL_ERROR = "Event About cannot be null";
    public static final String EVENTADDRESS_NULL_ERROR = "Event Address cannot be null";
    public static final String EVENTDATE_NULL_ERROR = "Event Date cannot be null";
    public static final String EVENTTIME_NULL_ERROR = "Event time cannot be null";
    public static final String INVALID_EVENT_IMAGE_URL_NULLERROR = "EVENT Image cannot be null";
    
    
    public static final String EVENT_INVALID_ERROR = "Event is Invalid";
    public static final String EVENTNAME_INVALID_ERROR = "Event Name should contain at least 8 characters with only alphabet";
    public static final String EVENTDESCRIPTION_INVALID_ERROR = "Event Description should contain at least 30 characters";
    public static final String EVENTABOUT_INVALID_ERROR = "Event Aboutshould contain at least 10 characters";
    public static final String EVENTADDRESS_INVALID_ERROR = "Event Address should contain at least 30 characters";
    public static final String EVENTDATE_INVALID_ERROR = "Event Date should not be in the past";
    public static final String EVENTTIME_INVALID_ERROR = "Event time should be between 9 am and 8 pm";
    public static final String EVENTPRICE_INVALID_ERROR = "Event Price should be minimum of 150";
    public static final String INVALID_EVENT_IMAGE_URL_ERROR = "EVENT Image is invalid";
}
