package com.fssa.betterme.exception;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	// Calling each super constructors for each of the types
	public ServiceException(String msg) {
		super(msg);
	}


}