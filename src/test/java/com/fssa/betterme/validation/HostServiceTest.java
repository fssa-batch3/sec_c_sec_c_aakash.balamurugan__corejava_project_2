package com.fssa.betterme.validation;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.*;
import com.fssa.betterme.server.EventHostService;

import com.fssa.betterme.validation.message.EventHostValidatorError;


 class HostServiceTest {

	EventHost valid = new EventHost("sandeep" , "9876543210" ,"sandeep@gmail.com");
	EventHost inValid = new EventHost("san67" , "98765hv210" ,"sandeepgmail.com");
	
	@Test 
	void ValidAddHostTest() throws DAOException {
		Assertions.assertTrue(EventHostService.addHost(valid));
	}

	@Test 
	void ValidUpdateHostTest() throws DAOException {
	
		
		Assertions.assertTrue(EventHostService.updateHost(valid));
	}
	
	@Test 
	void ValidReadAllHostTest() throws DAOException {
		
		Assertions.assertTrue(EventHostService.readAllHost());
	}
	
	
	@Test 
	void ValidDeleteEventTest() throws DAOException {
		Assertions.assertTrue(EventHostService.deleteHost(valid));
	}
	@Test 
	void inValidAddHostTest()  {
	
		try {
			Assertions.assertFalse(EventHostService.addHost(inValid));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, e.getMessage());
		}
		
	}

	@Test 
	void inValidUpdateHostTest() throws DAOException {
	
		
	
		try {
			Assertions.assertFalse(EventHostService.addHost(inValid));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, e.getMessage());
		}
	}
	

	@Test 
	void inValidDeleteHostTest() throws DAOException {
		
		try {
			Assertions.assertFalse(EventHostService.deleteHost(inValid));
			Assertions.fail();
		} catch (DAOException e) {
			Assertions.assertEquals(EventHostValidatorError.EVENTHOSTNAME_INVALID_ERROR, e.getMessage());
		}
	}

}
