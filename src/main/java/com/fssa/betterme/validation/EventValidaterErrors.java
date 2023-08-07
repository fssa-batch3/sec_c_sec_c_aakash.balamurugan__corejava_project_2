package com.fssa.betterme.validation;

public interface EventValidaterErrors {
  public static final String EVENT_NULL_ERROR ="Event cannot be null ";
  public static final String EVENTNAME_NULL_ERROR ="Event Name cannot be null ";
  public static final String EVENTDESCRIPTION_NULL_ERROR ="Event Description cannot be null ";
  public static final String EVENTADDRESS_NULL_ERROR ="Event Address cannot be null ";
  public static final String EVENTDATE_NULL_ERROR ="Event Date cannot be null ";
  public static final String EVENTTIME_NULL_ERROR ="Event time cannot be null ";

  
  
  public static final String EVENT_INVALID_ERROR ="Event is  Invalid ";
  public static final String EVENTNAME_INVALID_ERROR ="Event Name should contain atleast of 8 characters";
  public static final String EVENTDESCRIPTION_INVALID_ERROR ="Event Description should contain atleast of 30 characters  ";
  public static final String EVENTADDRESS_INVALID_ERROR ="Event Address should contain atleast of 30 characters ";
  public static final String EVENTDATE_INVALID_ERROR ="Event Date should not be in the past ";
  public static final String EVENTTIME_INVALID_ERROR ="Event time should be between 9 am and 8 pm ";
  public static final String EVENTPRICE_INVALID_ERROR ="Event Price should range between 150 and 500 ";
}
