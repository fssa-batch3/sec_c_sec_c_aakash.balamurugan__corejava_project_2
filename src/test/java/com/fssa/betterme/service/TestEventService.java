package com.fssa.betterme.service;





import java.time.LocalDate;



import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Trainner;

import com.fssa.betterme.model.Event;



 class TestEventService {  
	
	Trainner validHost = new Trainner("John", "9783473478", "John@gmail.com");
	
	// Assuming you have a valid Trainner object named 'validHost'

	Event validEvent = new Event(
	    "Bettet me day twelve",
	    "",
	    "it a valid event to be instesrt with  length of 30 characters",
	    "274, M.G.R main road, perugudi, chennai",
	    LocalDate.now().plusDays(1),
	    LocalTime.of(15, 0),
	    300.0,
	    "https://iili.io/HNOIrnj.jpg",
	    validHost
	);

	Event updateEvent = new Event(
	    "The One Secret To Beat Overthinking",
	    "",
	    "aakash it is an event conducted by betterme",
	    "274, M.G.R main road, perugudi, chenna",
	   
	    LocalDate.now().plusDays(1),
	    LocalTime.of(15, 0),
	    150.0,
	    "https://iili.io/HNOIrnj.jpg",
	    validHost
	);

	
	Event deleteEvent = new Event(
			"Mundhanai Storytelling Special by Srikumar",
		    "",
		    "aakash it is an event conducted by betterme",
		    "274, M.G.R main road, perugudi, chenna",
		   
		    LocalDate.now().plusDays(1),
		    LocalTime.of(15, 0),
		    150.0,
		    "https://iili.io/HNOIrnj.jpg",
		    validHost
		);


	
	
	


	
	@Test
	void testAddEvent()  {
		try {
			Assertions.assertTrue(EventService.addEvent(validEvent));
		} catch (EventValidationException | ServiceException e) {
		
			e.printStackTrace();
		}
	} 
	


	@Test 

	void testUpdateEvent()  {

		
		try {
			Assertions.assertTrue(EventService.updateEvent(EventService.getEventByEventName(deleteEvent)));
		} catch (ServiceException | EventValidationException e) {
		
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
		} catch (EventValidationException | ServiceException e) {
		
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
		Assertions.assertDoesNotThrow(()->EventService.getAllEventByDate(LocalDate.of(2023, 9,5)));
	}

	@Test 
	void testGetAllEventByRange() {
		Assertions.assertDoesNotThrow(()->EventService.getAllEventByRange(LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 30)));
	}
	
	




}
