package com.fssa.betterme.validation;


import java.util.regex.Matcher;

import java.util.regex.Pattern;

import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Trainer;
import com.fssa.betterme.validation.message.EventHostValidatorError;

public class TrainerValidator {

    /**
     * Validates if an event host is valid.
     *
     * @param eventHost The event host to be validated.
     * @return True if the event host is valid, false otherwise.
     * @throws EventValidationException If the event host is not valid.
     */
    public boolean isValidEventHost(Trainer trainer) throws EventValidationException {
        if (trainer == null) {
            throw new EventValidationException(EventHostValidatorError.EVENTHOST_NULL_ERROR);
        }
        
        isValidHostName(trainer.getTrainerName());
     
        isValidEmail(trainer.getEmail());

        return true;
    }


    public boolean isValidHostName(String name) throws EventValidationException {
        // Define the regex pattern for a name
    	  if(name==null ) {
         	 throw new EventValidationException(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR);
         }
        String namePattern = "[a-zA-Z ]+";
        
        Pattern pattern = Pattern.compile(namePattern);
        Matcher match = pattern.matcher(name);
      
        // Check if the name matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new EventValidationException(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR);
        }
    }




    public boolean isValidEmail(String email) throws EventValidationException {
        // Define the regex pattern for an email address
    	
    	 if( email==null) {
  		   throw new EventValidationException(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR);
       }
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher match = pattern.matcher(email);

        // Check if the email matches the pattern
        if( match.matches() ) {
        	return true;
        }else {
        	 throw new EventValidationException(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR);
        }
    }



}
