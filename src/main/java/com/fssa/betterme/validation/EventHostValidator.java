package com.fssa.betterme.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Rating;

public class EventHostValidator {

    public static boolean isValidEventHost(EventHost eventHost) {
        if (eventHost == null) {
            throw new IllegalArgumentException(EventHostValidatorError.EVENTHOST_NULL_ERROR);
        }
        
        isValidHostName(eventHost.getHostName());
        isValidContactNumber(eventHost.getContactNumber());
        isValidEmail(eventHost.getEmail());
//      isValidReview(eventHost.getReview());
        return true;

   
    }

    public static boolean isValidHostName(String name) {
        // Define the regex pattern for a name
    	  if(name==null ) {
         	 throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR);
         }
        String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(namePattern);
        Matcher match = pattern.matcher(name);
      
        // Check if the name matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR);
        }
    }

    public static boolean isValidContactNumber(String contactNumber) {
        // Define the regex pattern for a 10-digit numeric format
    	 if( contactNumber==null) {
    		   throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR);
         }
    	
        String contactNumberPattern = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(contactNumberPattern);
        Matcher match = pattern.matcher(contactNumber);
        // Check if the contact number matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR);
        }
     }


    public static boolean isValidEmail(String email) {
        // Define the regex pattern for an email address
    	
    	 if( email==null) {
  		   throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR);
       }
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher match = pattern.matcher(email);

        // Check if the email matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new IllegalArgumentException(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR);
        }
    }

//    public boolean isValidReview(Rating review) {
//        // Implement your review validation logic here, e.g., check if it's within a valid rating range
//        // For this example, we'll assume that any non-null review is valid
//    	if(review == null) {
//       	 throw new IllegalArgumentException(EventHostValidatorError.EVENTRATING_INVALID_ERROR);
//    	}
//    	else {
//    		return true;
//    	}
//    }
}
