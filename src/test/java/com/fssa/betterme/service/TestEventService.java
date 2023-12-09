package com.fssa.betterme.service;





import java.time.LocalDate;



import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.Trainer;

import com.fssa.betterme.model.Event;



 class TestEventService {  
	
	Trainer validHost = new Trainer(null, null, null, null, null, null, null, null, null, null);
	// Assuming you have a valid Trainner object named 'validHost'
	EventService ser = new EventService();
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
			Assertions.assertTrue(ser.addEvent(validEvent));
		} catch (EventValidationException | ServiceException e) {
		
			e.printStackTrace();
		}
	} 
	


	@Test 

	void testUpdateEvent()  {

		
		try {
			Assertions.assertTrue(ser.updateEvent(ser.getEventByEventName(deleteEvent)));
		} catch (ServiceException | EventValidationException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test 
	
	void testReadAllEvent() {
		
		Assertions.assertDoesNotThrow(()->ser.getAllEvents());
	}
	
	@Test 
	
	void testReadActiveEvent() {
		
		Assertions.assertDoesNotThrow(()->ser.getActiveEvents());
	}
	
	
	@Test 

	void testDeleteEvent() {
		try {
			Assertions.assertTrue(ser.deleteEvent(ser.getEventByEventName(deleteEvent)));
		} catch (EventValidationException | ServiceException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test 
	void testGetActiveEventByDate(){
		Assertions.assertDoesNotThrow(()->ser.getActiveEventByDate(LocalDate.of(2023, 8,25)));
	}

	@Test 
	void testGetActiveEventByRange()  {
		Assertions.assertDoesNotThrow(()->ser.getActiveEventByRange(LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 1)));
	}
	
	@Test 
	void testGetAllEventByDate()  {
		Assertions.assertDoesNotThrow(()->ser.getAllEventByDate(LocalDate.of(2023, 9,5)));
	}

	@Test 
	void testGetAllEventByRange() {
		Assertions.assertDoesNotThrow(()->ser.getAllEventByRange(LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 30)));
	}
	
	




}
