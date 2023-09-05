package com.fssa.betterme.validation;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.betterme.model.Gender;
import com.fssa.betterme.model.User;

import com.fssa.betterme.validation.message.UserValidatorError;
import com.fssa.betterme.exception.UserValidationException;

 class TestUserValidator {

	
    @Test
    void testIsValidUserValidUser() {
        User user = new User("John Doe", "johndoe@example.com", "StrongP@ss123", 9876543210l, Gender.MALE);
     
        try {
            assertTrue(UserValidator.isValidUser(user));
        } catch (UserValidationException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    void testIsValidUserNull() {
        User user = null;
        try {
			UserValidator.isValidUser(user);
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USER_NULL_ERROR, e.getMessage());
		}
    }

    @Test
    void testIsValidUserNameNull() {
        User user = new User(null, "johndoe@example.com", "StrongP@ss123", 1234567890L, Gender.MALE);
        try {
			UserValidator.isValidName(user.getUsername());
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USERNAME_NULL_ERROR, e.getMessage());
		}
    }

    @Test
    void testIsValidUserNameTooShort() {
        User user = new User("Jo", "johndoe@example.com", "StrongP@ss123", 1234567890L, Gender.MALE);
       
        
        try {
    			UserValidator.isValidName(user.getUsername());
    		} catch (UserValidationException e) {
    			Assertions.assertEquals(UserValidatorError.USERNAME_INVALID_ERROR, e.getMessage());
    		}
    }

    @Test
    void testIsValidUserInvalidGender() {
        User user = new User("John Doe", "johndoe@example.com", "StrongP@ss123", 1234567890L, null);
        try {
    			UserValidator.isValidGender(user.getGender());
    		} catch (UserValidationException e) {
    			Assertions.assertEquals(UserValidatorError.USERGENDER_NULL_ERROR, e.getMessage());
    		}
    }

    @Test
    void testIsValidUserInvalidMobileNumber() {
        User user = new User("John Doe", "johndoe@example.com", "StrongP@ss123", 123456789L, Gender.MALE);
        try {
    			UserValidator.isValidMobileNumber(user.getPhoneNumber());
    		} catch (UserValidationException e) {
    			Assertions.assertEquals(UserValidatorError.USER_MOBILENUMBER_INVALID_ERROR, e.getMessage());
    		}
    }

    @Test
    void testIsValidUserInvalidEmail() {
        User user = new User("John Doe", "invalid-email", "StrongP@ss123", 1234567890L, Gender.MALE);
        try {
			UserValidator.isValidEmail(user.getEmail());
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USEREMAIL_INVALID_ERROR, e.getMessage());
		}
    }

    @Test
    void testIsValidUserInvalidPassword() {
        User user = new User("John Doe", "johndoe@example.com", "weakpass", 1234567890L, Gender.MALE);
        try {
			UserValidator.isValidPassword(user.getPassword());
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USERPASS_INVALID_ERROR, e.getMessage());
		}
    }
    
    @Test
    void testIsValidUserEmailNull() {
        User user = new User("John Doe",null, "StrongP@ss123", 1234567890L, Gender.MALE);
        try {
			UserValidator.isValidEmail(user.getEmail());
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USEREMAIL_NULL_ERROR, e.getMessage());
		}
    }

    @Test
    void testIsValidUserPasswordNull() {
        User user = new User("John Doe", "johndoe@example.com", null, 1234567890L, Gender.MALE);
        try {
			UserValidator.isValidPassword(user.getPassword());
		} catch (UserValidationException e) {
			Assertions.assertEquals(UserValidatorError.USERPASS_NULL_ERROR, e.getMessage());
		}
    }
}

