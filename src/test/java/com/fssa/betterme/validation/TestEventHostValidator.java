package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.message.EventHostValidatorError;

 class TestEventHostValidator {
	
	EventHost validHost = new EventHost("vishali", "9876543210", "joe1@gmail.com");
	EventHost inValidHost = new EventHost("joe32uj493j", "9876543210ujn", "9876543210ujn.com");

	EventValidator validateEvent = new EventValidator();
	EventHostValidator validateHost = new EventHostValidator();
	
	@Test
	void testValidEventHost() throws DAOException {

		Assertions.assertTrue(EventHostValidator.isValidEventHost(validHost));
	}

	@Test
	void testInValidEventHostNull() {
		try {
			EventHostValidator.isValidEventHost(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOST_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNameTest() throws DAOException {
		validHost.setHostName(validHost.getHostName());
		Assertions.assertTrue(EventHostValidator.isValidHostName(validHost.getHostName()));
	}

	@Test
	void testInValidEventHostNameNull() {
		try {
			EventHostValidator.isValidHostName(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostName() {
		try {
			EventHostValidator.isValidHostName(inValidHost.getHostName());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNumber() throws DAOException {
		validHost.setContactNumber(validHost.getContactNumber());
		Assertions.assertTrue(EventHostValidator.isValidContactNumber(validHost.getContactNumber()));
	}

	@Test
	void testInValidEventHostNumberNull() {
		try {
			EventHostValidator.isValidContactNumber(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostNumber() {
		try {
			EventHostValidator.isValidContactNumber(inValidHost.getContactNumber());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostMail() throws DAOException {
		validHost.setEmail(validHost.getEmail());
		Assertions.assertTrue(EventHostValidator.isValidEmail(validHost.getEmail()));
	}

	@Test
	void testInValidEventHostMailNull() {
		try {
			EventHostValidator.isValidEmail(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostMail() {
		try {
			EventHostValidator.isValidEmail(inValidHost.getEmail());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR, ex.getMessage());
		}

	}
	
}
