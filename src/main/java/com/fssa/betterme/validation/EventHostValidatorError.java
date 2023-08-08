package com.fssa.betterme.validation;

public interface EventHostValidatorError {
	  public static final String EVENTHOST_NULL_ERROR ="EventHost cannot be null ";
	  public static final String EVENTHOSTNAME_NULL_ERROR ="Event host Name cannot be null ";
	  public static final String  EVENTHOSTNUMBER_NULL_ERROR = "Event host Contact number cannot be null";
	  public static final String  EVENTHOSTMAIL_NULL_ERROR = "Event host Contact mail cannot be null";
	  
	  
	  public static final String EVENTHOST_INVALID_ERROR ="Event host is  Invalid ";
	  public static final String EVENTHOSTNAME_INVALID_ERROR ="Event host Name should contain atleast of 8 characters";
	  public static final String EVENTHOSTNUMBER_INVALID_ERROR ="Event host number should have a valid mobile number ";
	  public static final String EVENTHOSTMAIL_INVALID_ERROR ="Event host number should have a valid email ";
	  public static final String EVENTRATING_INVALID_ERROR ="Event Rating can be only the following : GOOD , WORST , IMPRESIVE ";
	
	}

