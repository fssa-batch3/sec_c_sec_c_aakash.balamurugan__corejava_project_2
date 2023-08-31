package com.fssa.betterme.service;





import java.time.LocalDate;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.service.message.Constants;
import com.fssa.betterme.model.Event;



 class TestEventService { 
	
	EventHost validHost = new EventHost("Aakash", "9876543210", "aakash@gmail.com");
	
	Event validEvent = new Event("Bettet me day twelve", "it a valid event to be instesrt with  length of 30 characters",
			"274 ,M.G.R main road, perugudi , chennai ", "https://iili.io/HNOIrnj.jpg", LocalDate.now().plusDays(1), LocalTime.of(15, 00)
			, 150.00,validHost);
	
	Event updateEvent = new Event("Bettet me day one", "aakash it is a event conducted by betterme ",
			"it is a event conducted by betterme", "https://iili.io/HNOIrnj.jpg",LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);

	Event deleteEvent = new Event("Bettet me day five", "it is a event conducted by betterme ",
			"it is a event conducted by betterme","https://iili.io/HNOIrnj.jpg", LocalDate.now().plusDays(1), LocalTime.of(15, 00), 150.00,
			validHost);
	
	
	


	
	@Test
	void testAddEvent()  {
		try {
			Assertions.assertTrue(EventService.addEvent(validEvent));
		} catch (ValidationException | ServiceException e) {
		
			e.printStackTrace();
		}
	} 
	


	@Test 

	void testUpdateEvent()  {

		
		try {
			Assertions.assertTrue(EventService.updateEvent(EventService.getEventByEventName(deleteEvent)));
		} catch (ServiceException | ValidationException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test 
	
	void testReadAllEvent() {
		
		Assertions.assertDoesNotThrow(()->EventService.getAllEvents());
	}
	
	@Test 
	
	void testReadActiveEvent() {
		
		Assertions.assertDoesNotThrow(()->EventService.getActiveEvents());
	}
	
	
	@Test 

	void testDeleteEvent() {
		try {
			Assertions.assertTrue(EventService.deleteEvent(EventService.getEventByEventName(deleteEvent)));
		} catch (ValidationException | ServiceException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test 
	void testGetActiveEventByDate(){
		Assertions.assertDoesNotThrow(()->EventService.getActiveEventByDate(LocalDate.of(2023, 8,25)));
	}

	@Test 
	void testGetActiveEventByRange()  {
		Assertions.assertDoesNotThrow(()->EventService.getActiveEventByRange(LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 1)));
	}
	
	@Test 
	void testGetAllEventByDate()  {
		Assertions.assertDoesNotThrow(()->EventService.getAllEventByDate(LocalDate.of(2023, 8,25)));
	}

	@Test 
	void testGetAllEventByRange() {
		Assertions.assertDoesNotThrow(()->EventService.getAllEventByRange(LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 1)));
	}
	
	




}
