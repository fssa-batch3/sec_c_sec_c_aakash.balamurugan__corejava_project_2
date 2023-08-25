package com.fssa.betterme.service;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.*;
import com.fssa.betterme.service.EventHostService;



 class TestHostService {

	EventHost valid = new EventHost("joel" , "9876543210" ,"joel@gmail.com");
	EventHost Updatevalid = new EventHost("Yogi" , "9876543210" ,"yogi@gmail.com");
	
	@Test 
	void testAddHost() throws DAOException, ValidationException {
		Assertions.assertTrue(EventHostService.addHost(valid));
	}

	@Test 
	void testUpdateHost() throws DAOException, ValidationException {
	
		
		Assertions.assertTrue(EventHostService.updateHost(valid));
	}
	
	@Test 
	void testReadAllHost() throws DAOException {
		
		Assertions.assertDoesNotThrow(()->EventHostService.readAllHost());
	}
	
	
//	@Test 
//	void testDeleteHost() throws DAOException {
//		Assertions.assertTrue(EventHostService.deleteHost(valid));
//	}


}
