package com.fssa.betterme.service;



import org.junit.jupiter.api.Assertions;



import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.*;




 class TestHostService {

	Trainer valid = new Trainer("Eleana" , "7823452312" ,"Eleana@gmail.com");
	Trainer Updatevalid = new Trainer("Damon" , "9876543210" ,"Damon@gmail.com");
	TrainerService ser = new TrainerService();
	
	@Test 
	void testAddHost()  {
		try {
			Assertions.assertTrue(ser.addTrainer(valid));
		} catch (ServiceException | EventValidationException e) {
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}

	@Test 
	void testUpdateHost() {
	
		
		try {
			Assertions.assertTrue(ser.updateTrainer(Updatevalid));
		} catch (EventValidationException | ServiceException e) {
			Assertions.fail();
		}
	}
	
//	@Test 
//	void testDeleteHost() {
//		try {
//			Assertions.assertTrue(EventHostService.deleteHost(Updatevalid));
//		} catch (ValidationException | ServiceException e) {
//			System.out.print(e);
//			e.printStackTrace();
//			Assertions.fail();
//		}
//	}
	
	@Test 
	void testReadAllHostByEmail()  {
		Assertions.assertDoesNotThrow(()->ser.findTrainerByEmail(Updatevalid.getEmail()));
	}
	
	@Test 
	void testReadAllHostById()  {
		Assertions.assertDoesNotThrow(()->ser.findTrainerById(ser.findTrainerByEmail(Updatevalid.getEmail()).getId()));
	}
	
	@Test 
	void testReadAllHost()  {
		
		Assertions.assertDoesNotThrow(()->ser.readAllTrainer());
	}

	

	
	
	
	



}
