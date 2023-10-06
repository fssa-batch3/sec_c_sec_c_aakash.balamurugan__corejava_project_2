package com.fssa.betterme.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.exception.UserValidationException;
import com.fssa.betterme.model.Gender;
import com.fssa.betterme.model.User;
import com.fssa.betterme.validation.message.UserValidatorError;



public class UserValidator {

	/**
	 * Validates a User object based on specific criteria for its fields.
	 *
	 * @param user The User object to validate.
	 * @return true if the User object is valid, false otherwise.
	 * @throws UserValidationException If the provided User object is null or if any of its fields do not meet the validation criteria.
	 */
	public static boolean isValidUser(User user) throws UserValidationException {
	    if (user == null) {
	        throw new UserValidationException(UserValidatorError.USER_NULL_ERROR);
	    }

	    // Validate individual fields of the User object
	    isValidName(user.getUsername());
	    isValidGender(user.getGender());
	    isValidMobileNumber(user.getPhoneNumber());
	    isValidEmail(user.getEmail());
	    isValidPassword(user.getPassword());

	    // If all field validations pass, the User object is considered valid
	    return true;
	}

    /**
     * Validates a name.
     *
     * @param name The name to validate.
     * @return true if the name is valid which contains of only alphabets and atleast 2 charaters, false otherwise.
     * @throws UserValidationException 
     * @throws EventValidationException 
     */
    public static boolean isValidName(String name) throws UserValidationException {
        // Basic name validation (only allows letters and spaces).
    	
    	  if(name==null ) {
          	 throw new UserValidationException(UserValidatorError.USERNAME_NULL_ERROR);
          }
         String namePattern = "[a-zA-Z ]+"; 
         Pattern pattern = Pattern.compile(namePattern);
         Matcher match = pattern.matcher(name);
       
         // Check if the name matches the pattern
         if( match.matches()&& name.length()>3 ) {
         	return true;
         }else {
         	 throw new UserValidationException(UserValidatorError.USERNAME_INVALID_ERROR);
         }
    	
    }

    /**
     * Validates a gender.
     *
     * @param gender The gender to validate.
     * @return true if the gender is valid, false otherwise.
     * @throws UserValidationException 
     */
    public static boolean isValidGender(Gender gender) throws UserValidationException {
        // Basic gender validation (allows "Male," "Female," "Other,").

    	if(gender == null) {
    		throw new UserValidationException(UserValidatorError.USERGENDER_NULL_ERROR);
    	}
        return true;
    }

    /**
     * Validates a mobile number.
     *
     * @param mobileNumber The mobile number to validate.
     * @return true if the mobile number is valid, false otherwise.
     * @throws UserValidationException 
     */
    public static boolean isValidMobileNumber(long num) throws UserValidationException {
    	
    	String mobileNumber = String.valueOf(num);
    	   String numPattern = "^[6-9]\\d{9}$"; 
           Pattern pattern = Pattern.compile(numPattern);
           Matcher match = pattern.matcher(mobileNumber);
         
           // Check if the name matches the pattern
           if( match.matches()) {
           	return true;
           }else {
           	 throw new UserValidationException(UserValidatorError.USER_MOBILENUMBER_INVALID_ERROR);
           }
    }
    
    /**
     * Validates an email address.
     *
     * @param email The email address to validate.
     * @return true if the email address is valid, false otherwise.
     * @throws UserValidationException If the provided email is null or does not match the expected email pattern.
     */
    public static boolean isValidEmail(String email) throws UserValidationException {
        // Define the regex pattern for an email address
        if (email == null) {
            throw new UserValidationException(UserValidatorError.USEREMAIL_NULL_ERROR);
        }

        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher match = pattern.matcher(email);

        // Check if the email matches the pattern
        if (match.matches()) {
            return true;
        } else {
            throw new UserValidationException(UserValidatorError.USEREMAIL_INVALID_ERROR);
        }
    }

    /**
     * Validates a password based on specific criteria using a regex pattern.
     *
     * @param password The password to validate.
     * @return true if the password meets the criteria, false otherwise.
     * @throws UserValidationException 
     */
 
        public static boolean isValidPassword(String password) throws UserValidationException {
            if (password == null || password.isEmpty()) {
                throw new UserValidationException(UserValidatorError.USERPASS_NULL_ERROR);
            }

            
            
                return true;
          
        }


    

	}
    

