package com.fssa.betterme.validation;

import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.message.EventValidaterErrors;

import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidator {

    // Private constructor to prevent instantiation since this is a utility class.
     EventValidator() {
    }
    
  
    
    
   


	private final static int minPrice =150;
    private final static int maxPrice =500;

    // Validator methods

    /**
     * Validates if an event is valid.
     *
     * @param event The event to be validated.
     * @return True if the event is valid, false otherwise.
     * @throws ValidationException If the event is not valid.
     */
    public static boolean isValidEvent(Events event) throws ValidationException {
        if (event == null) {
            throw new ValidationException(EventValidaterErrors.EVENT_NULL_ERROR);
        }

        isValidEventName(event.getEventName());
        isValidEventDescription(event.getEventDescription());
        isValidEventAddress(event.getEventAddress());
        isValidEventDate(event.getEventDate());
        isValidEventTime(event.getEventTime());
        isValidPrice(event.getPrice());

        return true;
    }

    /**
     * Validates if the event name is valid.
     *
     * @param name The event name to be validated.
     * @return True if the event name is valid.
     * @throws ValidationException If the event name is not valid.
     */


	
	public static boolean isValidEventName(String name ) throws ValidationException{
		  if(name==null ||name.trim().isEmpty()) {
	         	 throw new ValidationException(EventValidaterErrors.EVENTNAME_NULL_ERROR);
	         }
	        String namePattern = "[a-zA-Z ]+";// regex pattern that the string should contain only alphabet
	        Pattern pattern = Pattern.compile(namePattern);
	        Matcher match = pattern.matcher(name);
	      
	        // Check if the name matches the pattern and have a length of 8 charaters
	        if( match.matches() && name.length()>=8 ) { // 
	        	return true;
	        }else {
	        	 throw new ValidationException(EventValidaterErrors.EVENTNAME_INVALID_ERROR);
	        }
		
	}
	
// validator for description as the description contains atleast of 30 charaters length
	    public static boolean isValidEventDescription(String eventDescription) throws ValidationException {
	        if(eventDescription == null || eventDescription.trim().isEmpty()) {
	        	throw new ValidationException(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR);
	        	
	        }	else if(eventDescription.length()>30) {
	    		return true;
			}else {
				throw new ValidationException(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR);
			}
	    }

	 // validator for description as the address contains atleast of 30 charaters length
	    public static boolean isValidEventAddress(String eventAddress) throws ValidationException {
	    	   if(eventAddress == null || eventAddress.trim().isEmpty()) {
	   			throw new ValidationException(EventValidaterErrors.EVENTADDRESS_NULL_ERROR);
		        	
		        }	else if(eventAddress.length()>30) {
		    		return true;
				}else {
					throw new ValidationException(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR);
				}
	    }

	
	 // validator for  the date should be not null and should not be in the past
	    public static boolean isValidEventDate(LocalDate eventDate) throws ValidationException {
	LocalDate today = LocalDate.now();
	        if(eventDate == null ) {
	        	throw new ValidationException(EventValidaterErrors.EVENTDATE_NULL_ERROR);
	        }else if(eventDate.isAfter(today)) {
	        	return true;
	        }else {
	        	throw new ValidationException(EventValidaterErrors.EVENTDATE_INVALID_ERROR);
	        }
	    }
	    
	 // validator for time as the time should be between 9 am and 8pm
	    public static boolean isValidEventTime(LocalTime eventTime) throws ValidationException {
	        if (eventTime == null) {
	        	throw new ValidationException(EventValidaterErrors.EVENTTIME_NULL_ERROR);
	        }

	        LocalTime minTime = LocalTime.of(8, 59); // Minimum time (9:00 AM)
	        LocalTime maxTime = LocalTime.of(21, 01); // Maximum time (8:00 PM)
	        
	        if(eventTime.isAfter(minTime) && eventTime.isBefore(maxTime)) {
	        	return true;
	        }else {
	        	throw new ValidationException(EventValidaterErrors.EVENTTIME_INVALID_ERROR);
	        }

	       
	    }

		 // validator for price should be in the range of 150 and 500 
	    public static boolean isValidPrice(double price) throws ValidationException {
	    	if(price >= minPrice && price <=maxPrice) {
	        return true;
	        }else {
	        	throw new ValidationException(EventValidaterErrors.EVENTPRICE_INVALID_ERROR);
	        }
	    }

	 
	

}
