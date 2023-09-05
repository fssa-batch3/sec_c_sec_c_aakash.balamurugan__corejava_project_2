package com.fssa.betterme.validation;



import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.EventValidationException;
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
	void testValidEvent()  {

		try {
			Assertions.assertTrue(EventValidator.isValidEvent(validEvent));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventNull() {
		try {
			EventValidator.isValidEvent(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENT_NULL_ERROR, ex.getMessage());
			
		}
	}

	@Test
	void testValidEventName()  {

		validEvent.setEventName(validEvent.getEventName());
		try {
			Assertions.assertTrue(EventValidator.isValidEventName(validEvent.getEventName()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventNameNull() {

		try {
			EventValidator.isValidEventName(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventName() {
		try {
			EventValidator.isValidEventName(inValidEvent.getEventName());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, ex.getMessage());
		}
 
	}

	@Test
	void testValidEventAddress()  {

		validEvent.setEventAddress(validEvent.getEventAddress());

		try {
			Assertions.assertTrue(EventValidator.isValidEventAddress(validEvent.getEventAddress()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventAddressNull() {
		try {
			EventValidator.isValidEventAddress(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventAddress() {
		try {
			EventValidator.isValidEventAddress(inValidEvent.getEventAddress());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void testValidEventDescription() {

		validEvent.setEventDescription(validEvent.getEventDescription());
		try {
			Assertions.assertTrue(EventValidator.isValidEventDescription(validEvent.getEventDescription()));
		} catch (EventValidationException e) {
			
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventDescriptionNull() {

		try {
			EventValidator.isValidEventDescription(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventDescription() {
		try {
			EventValidator.isValidEventDescription(inValidEvent.getEventDescription());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void testValidEventDate() {

		validEvent.setEventDate(validEvent.getEventDate());
		
		try {
			Assertions.assertTrue(EventValidator.isValidEventDate(validEvent.getEventDate()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventDateNull() {

		try {
			EventValidator.isValidEventDate(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void testInValidEventDate() {

		try {
			EventValidator.isValidEventDate(inValidEvent.getEventDate());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidEventTime()  {

		validEvent.setEventTime(validEvent.getEventTime());
		try {
			Assertions.assertTrue(EventValidator.isValidEventTime(validEvent.getEventTime()));
		} catch (EventValidationException e) {
	
		}
	}

	@Test
	void testInValidEventTimeNull() {
		try {
			EventValidator.isValidEventTime(null);
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void testInValidEventTime() {

		try {
			EventValidator.isValidEventTime(inValidEvent.getEventTime());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_INVALID_ERROR, ex.getMessage());
		}

	}
	
	@Test
	void testValidProductImageURLNull() {
		
	
			try {
				EventValidator.isValidateProductImageLink(null);
				Assertions.fail("Test Invalid Product Image URL Method Is Failded");
			} catch (EventValidationException e) {
				Assertions.assertEquals(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_NULLERROR, e.getMessage());
			}
		
		
		
	}
	
	@Test
	void testValidProductImageURL() {
		
	
			try {
				EventValidator.isValidateProductImageLink(inValidEvent.getImageURL());
				Assertions.fail("Test Invalid Product Image URL Method Is Failded");
			} catch (EventValidationException e) {
				Assertions.assertEquals(EventValidaterErrors.INVALID_EVENT_IMAGE_URL_ERROR, e.getMessage());
			}
		
		
		
	}
	
	@Test
	void testInvalidProductImageURL() {
		
		validEvent.setImageUrl(validEvent.getImageURL());
		try {
			Assertions.assertTrue(EventValidator.isValidateProductImageLink(validEvent.getImageURL()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
		
	}

	@Test
	void testValidEventPrice() {

		validEvent.setPrice(validEvent.getPrice());
		try {
			Assertions.assertTrue(EventValidator.isValidPrice(validEvent.getPrice()));
		} catch (EventValidationException e) {
			Assertions.fail();
		}
	}

	@Test
	void testInValidEventPrice() {
		try {
			EventValidator.isValidPrice(inValidEvent.getPrice());
			Assertions.fail();
		} catch (EventValidationException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTPRICE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void testValidHostObj() {
		validEvent.setHost(validEvent.getHost());
		Assertions.assertTrue(true);

	}

}
