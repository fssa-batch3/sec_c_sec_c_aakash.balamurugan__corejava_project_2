package com.fssa.betterme.exception;


public class EventValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	// Calling each super constructors for each of the types
	public EventValidationException(String msg) {
		super(msg);
	}


}