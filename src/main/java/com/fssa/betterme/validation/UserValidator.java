package com.fssa.betterme.validation;



import com.fssa.betterme.exception.UserException;
import com.fssa.betterme.objects.Gender;
import com.fssa.betterme.objects.Users;

public class UserValidator {

    public static boolean validate(Users user) throws UserException {
        return isValidUsername(user.getUsername()) &&
               isValidEmail(user.getEmail()) &&
               isValidPassword(user.getPassword()) &&
               isValidGender(user.getGender());
    }

    private static boolean isValidUsername(String username) throws UserException {
    	if(username.equals(null) && !username.isEmpty()) {
    		throw new UserException("User name should not be null");
    	}
        return true;
        }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}");
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6; // Add more password validation rules as needed
    }

    private static boolean isValidGender(Gender gender) {
        return gender != null;
    }
}

