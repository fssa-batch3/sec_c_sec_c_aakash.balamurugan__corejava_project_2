package com.fssa.betterme.validation;



import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.EventHost;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.server.EventService;
import com.fssa.betterme.validation.message.EventValidaterErrors;

 class EventServiceTest {
	
	EventHost validHost = new EventHost("Isac", "9876543210", "Isac@gmail.com");
	EventHost inValidHost = new EventHost("joe32uj493j", "9876543210ujn", "9876543210ujn.com");
	Events validEvent = new Events("yogi event", "it is a event conducted by betterme ",
			"it is a event conducted by betterme", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	Events inValidEvent = new Events("me", "it is not a valid", "it is not a valid", LocalDate.now().minusDays(1),
			LocalTime.of(23, 0), 0, inValidHost);
	EventValidator validateEvent = new EventValidator();
	EventHostValidator validateHost = new EventHostValidator();
	EventService service = new EventService();
	@Test 
	void ValidAddEventTest() throws DAOException, SQLException {
		Assertions.assertTrue(service.addEvent(validEvent));
	}

	@Test 
	void ValidUpdateEventTest() throws DAOException, SQLException {
	
		
		Assertions.assertTrue(service.updateEvent(validEvent));
	}
	
	@Test 
	void ValidReadAllEventTest() throws DAOException, SQLException {
		
		Assertions.assertTrue(service.getEvents());
	}
	
	
	@Test 
	void ValidDeleteEventTest() throws DAOException, SQLException {
		Assertions.assertTrue(service.deleteEvent(validEvent));
	}
	@Test 
	void inValidAddEventTest()  {
	
		try {
			Assertions.assertFalse(service.addEvent(inValidEvent));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, e.getMessage());
		}
		
	}

	@Test 
	void inValidUpdateEventTest() throws DAOException, SQLException {
	
		
	
		try {
			Assertions.assertFalse(service.updateEvent(inValidEvent));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, e.getMessage());
		}
	}
	

	@Test 
	void inValidDeleteEventTest() throws DAOException, SQLException {
		
		try {
			Assertions.assertFalse(service.deleteEvent(inValidEvent));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventValidaterErrors.EVENTNAME_INVALID_ERROR, e.getMessage());
		}
	}

}
