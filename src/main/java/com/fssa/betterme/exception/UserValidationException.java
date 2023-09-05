package com.fssa.betterme.exception;

/**
 * Custom exception for user validation errors.
 */
public class UserValidationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3072086542843624737L;

	public UserValidationException(String message) {
        super(message);
    }
}