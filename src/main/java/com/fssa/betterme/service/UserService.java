package com.fssa.betterme.service;

import java.util.List;

import com.fssa.betterme.dao.UserDao;
import com.fssa.betterme.exception.UserDAOException;
import com.fssa.betterme.exception.UserServiceException;
import com.fssa.betterme.exception.UserValidationException;
import com.fssa.betterme.model.User;
import com.fssa.betterme.validation.UserValidator;

public class UserService {
	
	static UserDao ser = new UserDao();

	   public static boolean addUser(User user) throws  UserValidationException, UserServiceException   {
	        try {
				if (UserValidator.isValidUser(user)) {
					if(!ser.doesUserExist(user.getEmail())) {

						ser.addUser(user);
				    return true;
				    }else {
				    	throw new UserValidationException("User already exists");
				    }
				}
			} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
	        return false;
	    }
	   
	   public static boolean updateUser(User user) throws  UserValidationException ,UserServiceException  {
	        try {
				if (UserValidator.isValidUser(user)) {
					if(ser.doesUserExist(user.getEmail())) {

						ser.updateUser(user);
				    return true;
				    }else {
				    	throw new UserValidationException("User doesn't exists");
				    }
				}
			} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
	        return false;
	    }
	   
	   public static boolean deleteUser(User user) throws  UserServiceException, UserValidationException   {
	        try {
				if (UserValidator.isValidUser(user)) {
					if(ser.doesUserExist(user.getEmail())) {

						ser.deleteUser(user);
				    return true;
				    }else {
				    	throw new UserValidationException("User doesn't exists");
				    }
				}
			} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
	        return false;
	    }
	   
	 
	    
	    public static List<User> getAllUsers() throws UserServiceException {
	        try {
	                      
	            return ser.readAllUsers(); 
	        } catch (UserDAOException e) {
	        	throw new UserServiceException(e.getMessage());
	        }
	    }

	    public static User getUserById(int id) throws UserServiceException{
	    	try {
	        return  UserDao.getUserById(id);

	    	} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
	    }
	    
	    public static User getUserByEmail(String email) throws UserServiceException, UserValidationException{
	    	try {
	    		if(ser.doesUserExist(email)) {

	    			return  ser.getUserByEmail(email);
	    		}else {
	    			throw new UserValidationException("User doesn't exists");
	    		}
	    	} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
			
	    }
}

