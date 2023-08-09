package com.fssa.betterme.validation;

import com.fssa.betterme.objects.Events;
import com.fssa.betterme.dao.DAOException;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidator {

	 EventValidator (){
		
	}
	    // Other attributes and methods remain the same...

	    // Validator methods
	// validation for valid event
	public static boolean isValidEvent(Events event) throws DAOException {
		if(event==null) {  // check is it null
			throw new DAOException(EventValidaterErrors.EVENT_NULL_ERROR);// throws exception if its null
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
	
	public static boolean isValidEventName(String name ) throws DAOException{
		  if(name==null ||name.trim().isEmpty()) {
	         	 throw new DAOException(EventValidaterErrors.EVENTNAME_NULL_ERROR);
	         }
	        String namePattern = "^[A-Za-z\s]+$";// ergex pattern that the string should contain only alphabet
	        Pattern pattern = Pattern.compile(namePattern);
	        Matcher match = pattern.matcher(name);
	      
	        // Check if the name matches the pattern
	        if( match.matches() && name.length()>=8 ) { // 
	        	return true;
	        }else {
	        	 throw new DAOException(EventValidaterErrors.EVENTNAME_INVALID_ERROR);
	        }
		
	}
	
// validator for description as the description contains atleast of 30 charaters length
	    public static boolean isValidEventDescription(String eventDescription) throws DAOException {
	        if(eventDescription == null || eventDescription.trim().isEmpty()) {
	        	throw new DAOException(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR);
	        	
	        }	else if(eventDescription.length()>30) {
	    		return true;
			}else {
				throw new DAOException(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR);
			}
	    }

	 // validator for description as the address contains atleast of 30 charaters length
	    public static boolean isValidEventAddress(String eventAddress) throws DAOException {
	    	   if(eventAddress == null || eventAddress.trim().isEmpty()) {
	   			throw new DAOException(EventValidaterErrors.EVENTADDRESS_NULL_ERROR);
		        	
		        }	else if(eventAddress.length()>30) {
		    		return true;
				}else {
					throw new DAOException(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR);
				}
	    }

	
	 // validator for  the date should be not null and should not be in the past
	    public static boolean isValidEventDate(LocalDate eventDate) throws DAOException {
	LocalDate today = LocalDate.now();
	        if(eventDate == null ) {
	        	throw new DAOException(EventValidaterErrors.EVENTDATE_NULL_ERROR);
	        }else if(eventDate.isAfter(today)) {
	        	return true;
	        }else {
	        	throw new DAOException(EventValidaterErrors.EVENTDATE_INVALID_ERROR);
	        }
	    }
	    
	 // validator for time as the time should be between 9 am and 8pm
	    public static boolean isValidEventTime(LocalTime eventTime) throws DAOException {
	        if (eventTime == null) {
	        	throw new DAOException(EventValidaterErrors.EVENTTIME_NULL_ERROR);
	        }

	        LocalTime minTime = LocalTime.of(8, 59); // Minimum time (9:00 AM)
	        LocalTime maxTime = LocalTime.of(21, 01); // Maximum time (8:00 PM)
	        
	        if(eventTime.isAfter(minTime) && eventTime.isBefore(maxTime)) {
	        	return true;
	        }else {
	        	throw new DAOException(EventValidaterErrors.EVENTTIME_INVALID_ERROR);
	        }

	       
	    }

		 // validator for price should be in the range of 150 and 500 
	    public static boolean isValidPrice(double price) throws DAOException {
	    	if(price >= 150 && price <=500) {
	        return true;
	        }else {
	        	throw new DAOException(EventValidaterErrors.EVENTPRICE_INVALID_ERROR);
	        }
	    }

	 
	

}
