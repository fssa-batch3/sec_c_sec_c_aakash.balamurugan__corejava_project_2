package com.fssa.betterme.service;





import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.model.Events;



 class TestEventService {
	
	EventHost validHost = new EventHost("Aakash", "9876543210", "aakash@gmail.com");
	
	Events validEvent = new Events("Bettet me day nineteen", "it a valid event to be instesrt with  length of 30 characters",
			"274 ,M.G.R main road, perugudi , chennai ", "https://iili.io/HNOIrnj.jpg", LocalDate.now().plusDays(1), LocalTime.of(15, 00)
			, 150.00,validHost);
	
	Events updateEvent = new Events("Bettet me day one", "aakash it is a event conducted by betterme ",
			"it is a event conducted by betterme", "https://iili.io/HNOIrnj.jpg",LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);

	Events deleteEvent = new Events("Bettet me day six", "it is a event conducted by betterme ",
			"it is a event conducted by betterme","https://iili.io/HNOIrnj.jpg", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	
	
	

	EventService service = new EventService();
	
	@Test
	@Order(1)
	void testAddEvent() throws DAOException, ValidationException {
		Assertions.assertTrue(service.addEvent(validEvent));
	} 

	@Test 
	@Order(2)
	void testUpdateEvent() throws ValidationException, DAOException {

		
		Assertions.assertTrue(service.updateEvent(updateEvent));
	}
	
	@Test 
	@Order(3)
	void testReadAllEvent() throws DAOException {
		
		Assertions.assertDoesNotThrow(()->service.getEvents());
	}
	
	
	@Test 
	@Order(4)
	void testDeleteEvent() throws DAOException, ValidationException  {
		Assertions.assertTrue(service.deleteEvent(deleteEvent));
	}
	
	@Test 
	void testGetEventByDate() throws DAOException {
		Assertions.assertDoesNotThrow(()->service.getEventByDate(LocalDate.of(2023, 8,25)));
	}

	@Test 
	void testGetEventByRange() throws DAOException {
		Assertions.assertDoesNotThrow(()->service.getEventByRange());
	}




}
