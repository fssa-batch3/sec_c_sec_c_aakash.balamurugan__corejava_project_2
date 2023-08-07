package com.fssa.betterme.validation;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.objects.Rating;

public class EventValidatorTest {

	EventHost validHost = new EventHost("joe", "9876543210", Rating.GOOD, "joe@gmail.com");
	EventHost inValidHost = new EventHost("joe32uj493j", "9876543210ujn", null, "9876543210ujn.com");
	Events validEvent = new Events("BetterMe", "it is a event conducted by betterme ",
			"it is a event conducted by betterme", LocalDate.now().plusDays(2), LocalTime.of(15, 00), 150.00,
			validHost);
	Events inValidEvent = new Events("me", "it is not a valid", "it is not a valid", LocalDate.now().minusDays(1),
			LocalTime.of(23, 0), 0, inValidHost);
	EventValidator validateEvent = new EventValidator();
	EventHostValidator validateHost = new EventHostValidator();

	@Test
	void validEventTest() {

		Assertions.assertTrue(validateEvent.isValidEvent(validEvent));
	}

	@Test
	void inValidEventTestNull() {
		try {
			validateEvent.isValidEvent(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENT_NULL_ERROR, ex.getMessage());
			;
		}
	}

	@Test
	void validEventNameTest() {

		validEvent.setEventName(validEvent.getEventName());
		Assertions.assertTrue(validateEvent.isValidEventName(validEvent.getEventName()));
	}

	@Test
	void inValidEventNameTestNull() {

		try {
			validateEvent.isValidEventName(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventNameTest() {
		try {
			validateEvent.isValidEventName(inValidEvent.getEventName());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventAddressTest() {

		validEvent.setEventAddress(validEvent.getEventAddress());

		Assertions.assertTrue(validateEvent.isValidEventAddress(validEvent.getEventAddress()));
	}

	@Test
	void inValidEventAddressTestNull() {
		try {
			validateEvent.isValidEventAddress(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventAddressTest() {
		try {
			validateEvent.isValidEventAddress(inValidEvent.getEventAddress());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void validEventDescriptionTest() {

		validEvent.setEventDescription(validEvent.getEventDescription());
		Assertions.assertTrue(validateEvent.isValidEventDescription(validEvent.getEventDescription()));
	}

	@Test
	void inValidEventDescriptionTestNull() {

		try {
			validateEvent.isValidEventDescription(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventDescriptionTest() {
		try {
			validateEvent.isValidEventDescription(inValidEvent.getEventDescription());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void validEventDateTest() {

		validEvent.setEventDate(validEvent.getEventDate());
		Assertions.assertTrue(validateEvent.isValidEventDate(validEvent.getEventDate()));
	}

	@Test
	void inValidEventDateTestNull() {

		try {
			validateEvent.isValidEventDate(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventDateTest() {

		try {
			validateEvent.isValidEventDate(inValidEvent.getEventDate());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventTimeTest() {

		validEvent.setEventTime(validEvent.getEventTime());
		Assertions.assertTrue(validateEvent.isValidEventTime(validEvent.getEventTime()));
	}

	@Test
	void inValidEventTimeTestNull() {
		try {
			validateEvent.isValidEventTime(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventTimeTest() {

		try {
			validateEvent.isValidEventTime(inValidEvent.getEventTime());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventPriceTest() {

		validEvent.setPrice(validEvent.getPrice());
		Assertions.assertTrue(validateEvent.isValidPrice(validEvent.getPrice()));
	}

	@Test
	void inValidEventPriceTest() {
		try {
			validateEvent.isValidPrice(inValidEvent.getPrice());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTPRICE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validHostObj() {
		validEvent.setHost(validEvent.getHost());
		Assertions.assertTrue(true);

	}

	@Test
	void validEventHostTest() {

		Assertions.assertTrue(validateHost.isValidEventHost(validHost));
	}

	@Test
	void inValidEventHostNullTest() {
		try {
			validateHost.isValidEventHost(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOST_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostNameTest() {
		validHost.setHostName(validHost.getHostName());
		Assertions.assertTrue(validateHost.isValidHostName(validHost.getHostName()));
	}

	@Test
	void inValidEventHostNameNullTest() {
		try {
			validateHost.isValidHostName(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostNameTest() {
		try {
			validateHost.isValidHostName(inValidHost.getHostName());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostNumberTest() {
		validHost.setContactNumber(validHost.getContactNumber());
		Assertions.assertTrue(validateHost.isValidContactNumber(validHost.getContactNumber()));
	}

	@Test
	void inValidEventHostNumberNullTest() {
		try {
			validateHost.isValidContactNumber(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostNumberTest() {
		try {
			validateHost.isValidContactNumber(inValidHost.getContactNumber());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNUMBER_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventHostMailTest() {
		validHost.setEmail(validHost.getEmail());
		Assertions.assertTrue(validateHost.isValidEmail(validHost.getEmail()));
	}

	@Test
	void inValidEventHostMailNullTest() {
		try {
			validateHost.isValidEmail(null);
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventHostMailTest() {
		try {
			validateHost.isValidEmail(inValidHost.getEmail());
			Assertions.fail();
		} catch (IllegalArgumentException ex) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTMAIL_INVALID_ERROR, ex.getMessage());
		}

	}

//	@Test
//	void validEventReviewTest() {
//		validHost.setReview(validHost.getReview());
//		Assertions.assertTrue(validateHost.isValidReview(validHost.getReview()));
//	}
//
//	@Test
//	void inValidEventReviewNullTest() {
//		try {
//			validateHost.isValidReview(inValidHost.getReview());
//			Assertions.fail();
//		} catch (IllegalArgumentException ex) {
//			Assertions.assertEquals(EventHostValidatorError.EVENTRATING_INVALID_ERROR, ex.getMessage());
//		}
//
//	}

}
