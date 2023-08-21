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


 class TestEventService {
	
	EventHost validHost = new EventHost("aakash", "9876543210", "aakash@gmail.com");
	Events validEvent = new Events("yogi event", "it is a event conducted by betterme ",
			"it is a event conducted by betterme", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	

	EventService service = new EventService();
	
	@Test 
	void testAddEvent() throws DAOException, SQLException {
		Assertions.assertTrue(service.addEvent(validEvent));
	} 

	@Test 
	void testUpdateEvent() throws DAOException, SQLException {
	
		
		Assertions.assertTrue(service.updateEvent(validEvent));
	}
	
	@Test 
	void testReadAllEvent() throws DAOException, SQLException {
		
		Assertions.assertTrue(service.getEvents());
	}
	
	
	@Test 
	void testDeleteEvent() throws DAOException, SQLException {
		Assertions.assertTrue(service.deleteEvent(validEvent));
	}
	
	@Test 
	void testGetEventByDate() throws DAOException, SQLException {
		Assertions.assertTrue(service.getEventByDate());
	}

	@Test 
	void testGetEventByRange() throws DAOException, SQLException {
		Assertions.assertTrue(service.getEventByRange());
	}




}
