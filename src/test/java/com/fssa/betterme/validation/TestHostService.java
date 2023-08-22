package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.objects.*;
import com.fssa.betterme.server.EventHostService;



 class TestHostService {

	EventHost valid = new EventHost("gokul" , "9876543210" ,"gokul@gmail.com");
	EventHost Updatevalid = new EventHost("yogi" , "9876543210" ,"yogi@gmail.com");
	
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
		
		Assertions.assertTrue(EventHostService.readAllHost());
	}
	
	
//	@Test 
//	void testDeleteHost() throws DAOException {
//		Assertions.assertTrue(EventHostService.deleteHost(valid));
//	}


}
