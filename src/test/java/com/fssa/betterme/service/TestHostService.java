package com.fssa.betterme.service;



import org.junit.jupiter.api.Assertions;



import org.junit.jupiter.api.Test;


import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.*;




 class TestHostService {

	EventHost valid = new EventHost("vishalii" , "9876543210" ,"vishaili@gmail.com");
	EventHost Updatevalid = new EventHost("Aakash" , "9876543210" ,"Aakash@gmail.com");
	
	@Test 
	void testAddHost()  {
		try {
			Assertions.assertTrue(EventHostService.addHost(valid));
		} catch (ServiceException | ValidationException e) {
			Assertions.fail();
		}
	}

	@Test 
	void testUpdateHost() {
	
		
		try {
			Assertions.assertTrue(EventHostService.updateHost(Updatevalid));
		} catch (ValidationException | ServiceException e) {
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
		Assertions.assertDoesNotThrow(()->EventHostService.readHostByEmail(Updatevalid.getEmail()));
	}
	
	@Test 
	void testReadAllHostById()  {
		Assertions.assertDoesNotThrow(()->EventHostService.readHostById(EventHostService.readHostByEmail(Updatevalid.getEmail()).getId()));
	}
	
	@Test 
	void testReadAllHost()  {
		
		Assertions.assertDoesNotThrow(()->EventHostService.readAllHost());
	}

	

	
	
	
	



}
