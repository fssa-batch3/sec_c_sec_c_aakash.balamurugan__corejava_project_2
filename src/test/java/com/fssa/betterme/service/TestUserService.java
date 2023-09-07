package com.fssa.betterme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.betterme.exception.UserServiceException;
import com.fssa.betterme.exception.UserValidationException;
import com.fssa.betterme.model.Gender;
import com.fssa.betterme.model.User;

class TestUserService {
	
     User validUser = new User("Suvetha", "suvetha@example.com", "Suvetha@123", 9876543210l, Gender.FEMALE);
	 User updateUser = new User("Aakash", "aakash@gmail.com", "Aakash@123", 9876543210l, Gender.MALE);
	 User deleteUser = new User("Sandeep", "Sandeep@gmail.com", "Sandeep@123", 9876543210l, Gender.MALE);
	 
	@Test 
	void testAddUser()  {
		
		UserService ser = new UserService();
			try {
				Assertions.assertTrue(UserService.addUser(validUser));
			} catch (UserValidationException | UserServiceException e) {
		
				Assertions.fail();
			}
		
	}
	
	@Test 
	void testUpdateUser()  {
			try {
				User user = UserService.getUserByEmail(updateUser.getEmail());
		
				Assertions.assertTrue(UserService.updateUser(user));
			} catch (UserValidationException | UserServiceException e) {
			
				Assertions.fail();
				
			}
		
	}
	
	@Test 
	void testdeleteUser()  {
		

			try {
				User user =  UserService.getUserByEmail(updateUser.getEmail());
				Assertions.assertTrue(UserService.deleteUser(user));
			} catch (UserValidationException | UserServiceException e) {
				
				Assertions.fail();
			}
		
	}
	

	
	@Test 
	void testReadAllUser()  {
		
		UserService ser = new UserService();
		Assertions.assertDoesNotThrow(()->UserService.getAllUsers());
			
	}
	
	@Test 
	void testGetUserByEmail()  {
		
		Assertions.assertDoesNotThrow(()->UserService.getUserByEmail(updateUser.getEmail()));
			
	}

}