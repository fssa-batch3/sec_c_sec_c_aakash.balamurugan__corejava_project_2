package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Trainner;
import com.fssa.betterme.validation.message.EventHostValidatorError;

 class TestEventHostValidator {
	
	Trainner validHost = new Trainner("vishali", "9876543210", "joe1@gmail.com");
	Trainner inValidHost = new Trainner("joe32uj493j", "9876543210ujn", "9876543210ujn.com");


	EventHostValidator validateHost = new EventHostValidator();
	
	@Test
	void testValidEventHost()  {

		try {
			Assertions.assertTrue(EventHostValidator.isValidEventHost(validHost));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNull() {
		try {
			EventHostValidator.isValidEventHost(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOST_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNameTest() {
		validHost.setHostName(validHost.getHostName());
		try {
			Assertions.assertTrue(EventHostValidator.isValidHostName(validHost.getHostName()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNameNull() {
		try {
			EventHostValidator.isValidHostName(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostName() {
		try {
			EventHostValidator.isValidHostName(inValidHost.getHostName());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNumber() {
		validHost.setContactNumber(validHost.getContactNumber());
		try {
			Assertions.assertTrue(EventHostValidator.isValidContactNumber(validHost.getContactNumber()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNumberNull() {
		try {
			EventHostValidator.isValidContactNumber(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostNumber() {
		try {
			EventHostValidator.isValidContactNumber(inValidHost.getContactNumber());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostMail(){
		validHost.setEmail(validHost.getEmail());
		try {
			Assertions.assertTrue(EventHostValidator.isValidEmail(validHost.getEmail()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostMailNull() {
		try {
			EventHostValidator.isValidEmail(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostMail() {
		try {
			EventHostValidator.isValidEmail(inValidHost.getEmail());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR, ex.getMessage());
		}

	}
	
}
