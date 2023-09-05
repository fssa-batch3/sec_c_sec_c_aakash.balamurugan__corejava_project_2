package com.fssa.betterme.validation.message;

public class UserValidatorError {
	
	
	 // Error messages for User validation
    public static final String USER_NULL_ERROR = "User cannot be null";
    public static final String USERNAME_NULL_ERROR = "User Name cannot be null";
    public static final String USERGENDER_NULL_ERROR = "Gender cannot be null";
    public static final String USEREMAIL_NULL_ERROR = "Email cannot be null";
    public static final String USERPASS_NULL_ERROR = "Password cannot be null and empty";
   
    

    public static final String USERNAME_INVALID_ERROR = "User Name should contain at least 2 characters with only alphabet";
    public static final String USER_MOBILENUMBER_INVALID_ERROR = "Mobile number should have 10 digits starting with 6,7,8,9";
    public static final String USEREMAIL_INVALID_ERROR = "Email should not be valid";
    public static final String USERPASS_INVALID_ERROR = "password should have atleast of one LowerCase , one UpperCase , one Special Character and a number";
 
}
