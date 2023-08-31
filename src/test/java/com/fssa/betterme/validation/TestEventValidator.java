package com.fssa.betterme.validation;



import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.validation.message.EventValidaterErrors;


 class TestEventValidator {

	EventHost validHost = new EventHost("vishali", "9876543210", "joe1@gmail.com");
	EventHost inValidHost = new EventHost("joe32uj493j", "9876543210ujn", "9876543210ujn.com");
	Event validEvent = new Event("yogi event", "it is a event conducted by betterme ",
			"it is a event conducted by betterme","https://iili.io/HNOIrnj.jpg", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	Event inValidEvent = new Event("me", "it is not a valid", "it is not a valid", "vd",LocalDate.now().minusDays(1),
			LocalTime.of(23, 0), 0, inValidHost);
	EventValidator validateEvent = new EventValidator();
	EventHostValidator validateHost = new EventHostValidator();

	
	
	@Test
	void testValidEvent() throws ValidationException {

		Assertions.assertTrue(EventValidator.isValidEvent(validEvent));
	}

	@Test
	void testInValidEventNull() {
		try {
			EventValidator.isValidEvent(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENT_NULL_ERROR, ex.getMessage());
			;
		}
	}

	@Test
	void testValidEventName() throws ValidationException {

		validEvent.setEventName(validEvent.getEventName());
		Assertions.assertTrue(EventValidator.isValidEventName(validEvent.getEventName()));
	}

	@Test
	void testInValidEventNameNull() {

		try {
			EventValidator.isValidEventName(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventName() {
		try {
			EventValidator.isValidEventName(inValidEvent.getEventName());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, ex.getMessage());
		}
 
	}

	@Test
	void testValidEventAddress() throws ValidationException {

		validEvent.setEventAddress(validEvent.getEventAddress());

		Assertions.assertTrue(EventValidator.isValidEventAddress(validEvent.getEventAddress()));
	}

	@Test
	void testInValidEventAddressNull() {
		try {
			EventValidator.isValidEventAddress(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventAddress() {
		try {
			EventValidator.isValidEventAddress(inValidEvent.getEventAddress());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void testValidEventDescription() throws ValidationException {

		validEvent.setEventDescription(validEvent.getEventDescription());
		Assertions.assertTrue(EventValidator.isValidEventDescription(validEvent.getEventDescription()));
	}

	@Test
	void testInValidEventDescriptionNull() {

		try {
			EventValidator.isValidEventDescription(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventDescription() {
		try {
			EventValidator.isValidEventDescription(inValidEvent.getEventDescription());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void testValidEventDate() throws ValidationException {

		validEvent.setEventDate(validEvent.getEventDate());
		Assertions.assertTrue(EventValidator.isValidEventDate(validEvent.getEventDate()));
	}

	@Test
	void testInValidEventDateNull() {

		try {
			EventValidator.isValidEventDate(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventDate() {

		try {
			EventValidator.isValidEventDate(inValidEvent.getEventDate());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventTime() throws ValidationException {

		validEvent.setEventTime(validEvent.getEventTime());
		Assertions.assertTrue(EventValidator.isValidEventTime(validEvent.getEventTime()));
	}

	@Test
	void testInValidEventTimeNull() {
		try {
			EventValidator.isValidEventTime(null);
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventTime() {

		try {
			EventValidator.isValidEventTime(inValidEvent.getEventTime());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_INVALID_ERROR, ex.getMessage());
		}

	}
	
	@Test
	void testValidProductImageURLNull() {
		
	
			try {
				EventValidator.isValidateProductImageLink(null);
				Assertions.fail("Test Invalid Product Image URL Method Is Failded");
			} catch (ValidationException e) {
				Assertions.assertEquals(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_NULLERROR, e.getMessage());
			}
		
		
		
	}
	
	@Test
	void testValidProductImageURL() {
		
	
			try {
				EventValidator.isValidateProductImageLink(inValidEvent.getImageURL());
				Assertions.fail("Test Invalid Product Image URL Method Is Failded");
			} catch (ValidationException e) {
				Assertions.assertEquals(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_ERROR, e.getMessage());
			}
		
		
		
	}
	
	@Test
	void testInvalidProductImageURL() throws ValidationException {
		
		validEvent.setImageUrl(validEvent.getImageURL());
		Assertions.assertTrue(EventValidator.isValidateProductImageLink(validEvent.getImageURL()));
		
	}

	@Test
	void testValidEventPrice() throws ValidationException {

		validEvent.setPrice(validEvent.getPrice());
		Assertions.assertTrue(EventValidator.isValidPrice(validEvent.getPrice()));
	}

	@Test
	void testInValidEventPrice() {
		try {
			EventValidator.isValidPrice(inValidEvent.getPrice());
			Assertions.fail();
		} catch (ValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTPRICE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidHostObj() {
		validEvent.setHost(validEvent.getHost());
		Assertions.assertTrue(true);

	}

}
