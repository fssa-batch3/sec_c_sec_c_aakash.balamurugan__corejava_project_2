package com.fssa.betterme.validation;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.validation.message.EventValidaterErrors;

public class EventValidator {

    // Private constructor to prevent instantiation since this is a utility class.
     public EventValidator() {
    }
    
  
    
    
   

 	private static final  LocalTime START =LocalTime.of(8, 59);
    private static final  LocalTime END  =LocalTime.of(21, 01);
	private static final  int MIN_PRICE =150;


    // Validator methods

    /**
     * Validates if an event is valid.
     *
     * @param event The event to be validated.
     * @return True if the event is valid, false otherwise.
     * @throws EventValidationException If the event is not valid.
     */
    public boolean isValidEvent(Event event) throws EventValidationException {
        if (event == null) {
            throw new EventValidationException(EventValidaterErrors.EVENT_NULL_ERROR);
        }

        isValidEventName(event.getEventName());
        isValidEventAbout(event.getEventAbout());
        isValidEventDescription(event.getEventDescription());
        isValidEventAddress(event.getEventAddress());
        isValidEventDate(event.getEventDate());
        isValidateProductImageLink(event.getImageUrl());
        isValidEventTime(event.getEventTime());
        isValidPrice(event.getPrice());

        return true;
    }



	/**
     * Validates if the event name is valid.
     *
     * @param name The event name to be validated.
     * @return True if the event name is valid.
     * @throws EventValidationException If the event name is not valid.
     */


	
	public boolean isValidEventName(String name ) throws EventValidationException{
		  if(name==null ||name.trim().isEmpty()) {
	         	 throw new EventValidationException(EventValidaterErrors.EVENTNAME_NULL_ERROR);
	         }
	        String namePattern = "[a-zA-Z ]+";// regex pattern that the string should contain only alphabet
	        Pattern pattern = Pattern.compile(namePattern);
	        Matcher match = pattern.matcher(name);
	      
	        // Check if the name matches the pattern and have a length of 8 charaters
	        if( match.matches() && name.length()>=8 ) { // 
	        	return true;
	        }else {
	        	 throw new EventValidationException(EventValidaterErrors.EVENTNAME_INVALID_ERROR);
	        }
		
	}
	
	
	// validator for description as the description contains atleast of 30 charaters length
    public boolean isValidEventDescription(String eventDescription) throws EventValidationException {
        if(eventDescription == null || eventDescription.trim().isEmpty()) {
        	throw new EventValidationException(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR);
        	
        }	else if(eventDescription.length()>30) {
    		return true;
		}else {
			throw new EventValidationException(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR);
		}
    }
    
// validator for description as the description contains atleast of 30 charaters length
    public boolean isValidEventAbout(String eventAbout) throws EventValidationException {
	        if(eventAbout == null || eventAbout.trim().isEmpty()) {
	        	throw new EventValidationException(EventValidaterErrors.EVENTABOUT_NULL_ERROR);
	        	
	        }	else if(eventAbout.length()>10) {
	    		return true;
			}else {
				throw new EventValidationException(EventValidaterErrors.EVENTABOUT_INVALID_ERROR);
			}
	    }

	 // validator for description as the address contains atleast of 30 charaters length
	    public boolean isValidEventAddress(String eventAddress) throws EventValidationException {
	    	   if(eventAddress == null || eventAddress.trim().isEmpty()) {
	   			throw new EventValidationException(EventValidaterErrors.EVENTADDRESS_NULL_ERROR);
		        	
		        }	else if(eventAddress.length()>30) {
		    		return true;
				}else {
					throw new EventValidationException(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR);
				}
	    }

	
	 // validator for  the date should be not null and should not be in the past
	    public boolean isValidEventDate(LocalDate eventDate) throws EventValidationException {
	LocalDate today = LocalDate.now();
	        if(eventDate == null ) {
	        	throw new EventValidationException(EventValidaterErrors.EVENTDATE_NULL_ERROR);
	        }else if(eventDate.isAfter(today)) {
	        	return true;
	        }else {
	        	throw new EventValidationException(EventValidaterErrors.EVENTDATE_INVALID_ERROR);
	        }
	    }
	    
	 // validator for time as the time should be between 9 am and 8pm
	    public boolean isValidEventTime(LocalTime eventTime) throws EventValidationException {
	        if (eventTime == null) {
	        	throw new EventValidationException(EventValidaterErrors.EVENTTIME_NULL_ERROR);
	        }

	        LocalTime minTime = START ;// Minimum time (9:00 AM)
	        LocalTime maxTime = END; // Maximum time (8:00 PM)
	        
	        if(eventTime.isAfter(minTime) && eventTime.isBefore(maxTime)) {
	        	return true;
	        }else {
	        	throw new EventValidationException(EventValidaterErrors.EVENTTIME_INVALID_ERROR);
	        }

	       
	    }
	    
		public boolean isValidateProductImageLink(String url) throws EventValidationException {

			/*
			 * Product Image URL Validation. If the product Image URL is Null or Empty It
			 * will throw the Exception.
			 */ 

			if (url == null || "".equals(url.trim())) {
				throw new EventValidationException(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_NULLERROR);
			}

			/*
			 *  Image URL Regex Pattern Validate Code
			 */
			boolean isMatch = Pattern.matches("(http)?s?:?(\\/\\/[^\"']*\\.(?:png|jpg|jpeg|gif|svg|webp))", url);

			/*
			 *  If the patter is not Matched it will throw the Exception Otherwise it's True.
			 */
			if (!isMatch) {
				throw new EventValidationException(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_ERROR);
			}

			return true;

		}

		 // validator for price should be in the range of 150 and 500 
	    public boolean isValidPrice(double price) throws EventValidationException {
	    	if(price >= MIN_PRICE ) {
	        return true;
	        }else {
	        	throw new EventValidationException(EventValidaterErrors.EVENTPRICE_INVALID_ERROR);
	        }
	    }

	 
	

}
