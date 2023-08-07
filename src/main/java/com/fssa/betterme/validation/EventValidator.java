package com.fssa.betterme.validation;

import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.EventHostValidator;

import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidator {

	    // Other attributes and methods remain the same...

	    // Validator methods
	
	public static boolean isValidEvent(Events event) throws IllegalArgumentException {
		if(event==null) {
			throw new IllegalArgumentException(EventValidaterErrors.EVENT_NULL_ERROR);
		}
		
		isValidEventName(event.getEventName());
		isValidEventDescription(event.getEventDescription());
		isValidEventAddress(event.getEventAddress());
		isValidEventDate(event.getEventDate());
		isValidEventTime(event.getEventTime());
		isValidPrice(event.getPrice());
		EventHostValidator.isValidEventHost(event.getHost());
	return true;
	
	}
	
	public static boolean isValidEventName(String name ) throws IllegalArgumentException{
		  if(name==null ||name.trim().isEmpty()) {
	         	 throw new IllegalArgumentException(EventValidaterErrors.EVENTNAME_NULL_ERROR);
	         }
	        String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
	        Pattern pattern = Pattern.compile(namePattern);
	        Matcher match = pattern.matcher(name);
	      
	        // Check if the name matches the pattern
	        if( match.matches() && name.length()>=8 ) {
	        	return true;
	        }else {
	        	 throw new IllegalArgumentException(EventValidaterErrors.EVENTNAME_INVALID_ERROR);
	        }
		
	}
	

	    public static boolean isValidEventDescription(String eventDescription) {
	        if(eventDescription == null || eventDescription.trim().isEmpty()) {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR);
	        	
	        }	else if(eventDescription.length()>30) {
	    		return true;
			}else {
				throw new IllegalArgumentException(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR);
			}
	    }

	    public static boolean isValidEventAddress(String eventAddress) {
	    	   if(eventAddress == null || eventAddress.trim().isEmpty()) {
	   			throw new IllegalArgumentException(EventValidaterErrors.EVENTADDRESS_NULL_ERROR);
		        	
		        }	else if(eventAddress.length()>30) {
		    		return true;
				}else {
					throw new IllegalArgumentException(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR);
				}
	    }

	
	    
	    public static boolean isValidEventDate(LocalDate eventDate) {
	LocalDate today = LocalDate.now();
	        if(eventDate == null ) {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTDATE_NULL_ERROR);
	        }else if(eventDate.isAfter(today)) {
	        	return true;
	        }else {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTDATE_INVALID_ERROR);
	        }
	    }
	    
	    
	    public static boolean isValidEventTime(LocalTime eventTime) {
	        if (eventTime == null) {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTTIME_NULL_ERROR);
	        }

	        LocalTime minTime = LocalTime.of(8, 59); // Minimum time (9:00 AM)
	        LocalTime maxTime = LocalTime.of(21, 01); // Maximum time (8:00 PM)
	        
	        if(eventTime.isAfter(minTime) && eventTime.isBefore(maxTime)) {
	        	return true;
	        }else {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTTIME_INVALID_ERROR);
	        }

	       
	    }

	    public static boolean isValidPrice(double price) {
	    	if(price >= 150 && price <=500) {
	        return true;
	        }else {
	        	throw new IllegalArgumentException(EventValidaterErrors.EVENTPRICE_INVALID_ERROR);
	        }
	    }

	 
	

}
