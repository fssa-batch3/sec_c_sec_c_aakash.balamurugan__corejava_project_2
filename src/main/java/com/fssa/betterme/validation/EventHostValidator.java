package com.fssa.betterme.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.betterme.dao.DAOException;
import com.fssa.betterme.objects.EventHost;

public class EventHostValidator {

    public static boolean isValidEventHost(EventHost eventHost) throws DAOException {
        if (eventHost == null) {
            throw new DAOException(EventHostValidatorError.EVENTHOST_NULL_ERROR);
        }
        
        isValidHostName(eventHost.getHostName());
        isValidContactNumber(eventHost.getContactNumber());
        isValidEmail(eventHost.getEmail());

        return true;

   
    }

    public static boolean isValidHostName(String name) throws DAOException {
        // Define the regex pattern for a name
    	  if(name==null ) {
         	 throw new DAOException(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR);
         }
        String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(namePattern);
        Matcher match = pattern.matcher(name);
      
        // Check if the name matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new DAOException(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR);
        }
    }

    public static boolean isValidContactNumber(String contactNumber) throws DAOException {
        // Define the regex pattern for a 10-digit numeric format
    	 if( contactNumber==null) {
    		   throw new DAOException(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR);
         }
    	
        String contactNumberPattern = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(contactNumberPattern);
        Matcher match = pattern.matcher(contactNumber);
        // Check if the contact number matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new DAOException(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR);
        }
     }


    public static boolean isValidEmail(String email) throws DAOException {
        // Define the regex pattern for an email address
    	
    	 if( email==null) {
  		   throw new DAOException(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR);
       }
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher match = pattern.matcher(email);

        // Check if the email matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new DAOException(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR);
        }
    }



}
