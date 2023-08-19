package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.*;
import com.fssa.betterme.server.EventHostService;



 class TestHostService {

	EventHost valid = new EventHost("Isac" , "9876543210" ,"Isac@gmail.com");
	
	@Test 
	void testAddHost() throws DAOException {
		Assertions.assertTrue(EventHostService.addHost(valid));
	}

	@Test 
	void testUpdateHost() throws DAOException {
	
		
		Assertions.assertTrue(EventHostService.updateHost(valid));
	}
	
	@Test 
	void testReadAllHost() throws DAOException {
		
		Assertions.assertTrue(EventHostService.readAllHost());
	}
	
	
	@Test 
	void testDeleteHost() throws DAOException {
		Assertions.assertTrue(EventHostService.deleteHost(valid));
	}


}
