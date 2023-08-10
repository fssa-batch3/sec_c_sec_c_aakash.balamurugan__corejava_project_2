package com.fssa.betterme.validation;


import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.dao.DAOException;
import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.server.Service;


 class EventValidatorTest {

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
	void validEventTest() throws DAOException {

		Assertions.assertTrue(EventValidator.isValidEvent(validEvent));
	}

	@Test
	void inValidEventTestNull() {
		try {
			EventValidator.isValidEvent(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENT_NULL_ERROR, ex.getMessage());
			;
		}
	}

	@Test
	void validEventNameTest() throws DAOException {

		validEvent.setEventName(validEvent.getEventName());
		Assertions.assertTrue(EventValidator.isValidEventName(validEvent.getEventName()));
	}

	@Test
	void inValidEventNameTestNull() {

		try {
			EventValidator.isValidEventName(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventNameTest() {
		try {
			EventValidator.isValidEventName(inValidEvent.getEventName());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, ex.getMessage());
		}
 
	}

	@Test
	void validEventAddressTest() throws DAOException {

		validEvent.setEventAddress(validEvent.getEventAddress());

		Assertions.assertTrue(EventValidator.isValidEventAddress(validEvent.getEventAddress()));
	}

	@Test
	void inValidEventAddressTestNull() {
		try {
			EventValidator.isValidEventAddress(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventAddressTest() {
		try {
			EventValidator.isValidEventAddress(inValidEvent.getEventAddress());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTADDRESS_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void validEventDescriptionTest() throws DAOException {

		validEvent.setEventDescription(validEvent.getEventDescription());
		Assertions.assertTrue(EventValidator.isValidEventDescription(validEvent.getEventDescription()));
	}

	@Test
	void inValidEventDescriptionTestNull() {

		try {
			EventValidator.isValidEventDescription(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventDescriptionTest() {
		try {
			EventValidator.isValidEventDescription(inValidEvent.getEventDescription());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDESCRIPTION_INVALID_ERROR, ex.getMessage());
		}
	}

	@Test
	void validEventDateTest() throws DAOException {

		validEvent.setEventDate(validEvent.getEventDate());
		Assertions.assertTrue(EventValidator.isValidEventDate(validEvent.getEventDate()));
	}

	@Test
	void inValidEventDateTestNull() {

		try {
			EventValidator.isValidEventDate(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_NULL_ERROR, ex.getMessage());
		}

	}

	@Test
	void inValidEventDateTest() {

		try {
			EventValidator.isValidEventDate(inValidEvent.getEventDate());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTDATE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventTimeTest() throws DAOException {

		validEvent.setEventTime(validEvent.getEventTime());
		Assertions.assertTrue(EventValidator.isValidEventTime(validEvent.getEventTime()));
	}

	@Test
	void inValidEventTimeTestNull() {
		try {
			EventValidator.isValidEventTime(null);
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_NULL_ERROR, ex.getMessage());
		}
	}

	@Test
	void inValidEventTimeTest() {

		try {
			EventValidator.isValidEventTime(inValidEvent.getEventTime());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTTIME_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validEventPriceTest() throws DAOException {

		validEvent.setPrice(validEvent.getPrice());
		Assertions.assertTrue(EventValidator.isValidPrice(validEvent.getPrice()));
	}

	@Test
	void inValidEventPriceTest() {
		try {
			EventValidator.isValidPrice(inValidEvent.getPrice());
			Assertions.fail();
		} catch (DAOException ex) {
			Assertions.assertEquals(EventValidaterErrors.EVENTPRICE_INVALID_ERROR, ex.getMessage());
		}

	}

	@Test
	void validHostObj() {
		validEvent.setHost(validEvent.getHost());
		Assertions.assertTrue(true);

	}

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
	
	
	
	@Test 
	void ValidAddEventTest() throws DAOException, SQLException {
		Assertions.assertTrue(Service.addEvent(validEvent));
	}

	@Test 
	void ValidUpdateEventTest() throws DAOException, SQLException {
	
		
		Assertions.assertTrue(Service.updateEvent(validEvent));
	}
	
	@Test 
	void ValidReadAllEventTest() throws DAOException, SQLException {
		
		Assertions.assertTrue(Service.getEvents());
	}
	
	
	@Test 
	void ValidDeleteEventTest() throws DAOException, SQLException {
		Assertions.assertTrue(Service.deleteEvent(validEvent));
	}


}
