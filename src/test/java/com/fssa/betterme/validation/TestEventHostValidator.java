package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Trainer;
import com.fssa.betterme.validation.message.EventHostValidatorError;

 class TestEventHostValidator {
	
	Trainer validHost = new Trainer("vishali", "9876543210", "joe1@gmail.com");
	Trainer inValidHost = new Trainer("joe32uj493j", "9876543210ujn", "9876543210ujn.com");


	TrainerValidator validateHost = new TrainerValidator();
	
	@Test
	void testValidEventHost()  {

		try {
			Assertions.assertTrue(TrainerValidator.isValidEventHost(validHost));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNull() {
		try {
			TrainerValidator.isValidEventHost(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOST_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNameTest() {
		validHost.setTrainerName(validHost.getTrainerName());
		try {
			Assertions.assertTrue(TrainerValidator.isValidHostName(validHost.getTrainerName()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNameNull() {
		try {
			TrainerValidator.isValidHostName(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostName() {
		try {
			TrainerValidator.isValidHostName(inValidHost.getTrainerName());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostNumber() {
		validHost.setContactNumber(validHost.getContactNumber());
		try {
			Assertions.assertTrue(TrainerValidator.isValidContactNumber(validHost.getContactNumber()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostNumberNull() {
		try {
			TrainerValidator.isValidContactNumber(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostNumber() {
		try {
			TrainerValidator.isValidContactNumber(inValidHost.getContactNumber());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventHostMail(){
		validHost.setEmail(validHost.getEmail());
		try {
			Assertions.assertTrue(TrainerValidator.isValidEmail(validHost.getEmail()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventHostMailNull() {
		try {
			TrainerValidator.isValidEmail(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventHostMail() {
		try {
			TrainerValidator.isValidEmail(inValidHost.getEmail());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR, ex.getMessage());
		}

	}
	
}
