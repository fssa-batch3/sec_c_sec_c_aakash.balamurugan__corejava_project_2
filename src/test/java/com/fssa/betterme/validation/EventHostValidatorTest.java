package com.fssa.betterme.validation;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.message.EventHostValidatorError;

public class EventHostValidatorTest {
	
	EventHost validHost = new EventHost("vishali", "9876543210", "joe1@gmail.com");
	EventHost inValidHost = new EventHost("joe32uj493j", "9876543210ujn", "9876543210ujn.com");
	Events validEvent = new Events("yogi event", "it is a event conducted by betterme ",
			"it is a event conducted by betterme", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	Events inValidEvent = new Events("me", "it is not a valid", "it is not a valid", LocalDate.now().minusDays(1),
			LocalTime.of(23, 0), 0, inValidHost);
	EventValidator validateEvent = new EventValidator();
	EventHostValidator validateHost = new EventHostValidator();
	
	@Test
	void validEventHostTest() throws DAOException {

		Assertions.assertTrue(EventHostValidator.isValidEventHost(validHost));
	}

	@Test
	void inValidEventHostNullTest() {
		try {
			EventHostValidator.isValidEventHost(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOST_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostNameTest() throws DAOException {
		validHost.setHostName(validHost.getHostName());
		Assertions.assertTrue(EventHostValidator.isValidHostName(validHost.getHostName()));
	}

	@Test
	void inValidEventHostNameNullTest() {
		try {
			EventHostValidator.isValidHostName(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostNameTest() {
		try {
			EventHostValidator.isValidHostName(inValidHost.getHostName());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostNumberTest() throws DAOException {
		validHost.setContactNumber(validHost.getContactNumber());
		Assertions.assertTrue(EventHostValidator.isValidContactNumber(validHost.getContactNumber()));
	}

	@Test
	void inValidEventHostNumberNullTest() {
		try {
			EventHostValidator.isValidContactNumber(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostNumberTest() {
		try {
			EventHostValidator.isValidContactNumber(inValidHost.getContactNumber());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostMailTest() throws DAOException {
		validHost.setEmail(validHost.getEmail());
		Assertions.assertTrue(EventHostValidator.isValidEmail(validHost.getEmail()));
	}

	@Test
	void inValidEventHostMailNullTest() {
		try {
			EventHostValidator.isValidEmail(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostMailTest() {
		try {
			EventHostValidator.isValidEmail(inValidHost.getEmail());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR, ex.getMessage());
		}

	}
	
}
