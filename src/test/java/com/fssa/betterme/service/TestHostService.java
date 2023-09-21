package com.fssa.betterme.service;



import org.junit.jupiter.api.Assertions;



import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.model.*;




 class TestHostService {

	Trainner valid = new Trainner("Eleana" , "7823452312" ,"Eleana@gmail.com");
	Trainner Updatevalid = new Trainner("Damon" , "9876543210" ,"Damon@gmail.com");
	
	@Test 
	void testAddHost()  {
		try {
			Assertions.assertTrue(EventHostService.addTrainer(valid));
		} catch (ServiceException | EventValidationException e) {
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}

	@Test 
	void testUpdateHost() {
	
		
		try {
			Assertions.assertTrue(EventHostService.updateTrainer(Updatevalid));
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
		Assertions.assertDoesNotThrow(()->EventHostService.findTrainerByEmail(Updatevalid.getEmail()));
	}
	
	@Test 
	void testReadAllHostById()  {
		Assertions.assertDoesNotThrow(()->EventHostService.findTrainerById(EventHostService.findTrainerByEmail(Updatevalid.getEmail()).getId()));
	}
	
	@Test 
	void testReadAllHost()  {
		
		Assertions.assertDoesNotThrow(()->EventHostService.readAllTrainer());
	}

	

	
	
	
	



}
