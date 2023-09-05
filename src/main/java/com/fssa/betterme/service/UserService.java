package com.fssa.betterme.service;

import java.util.List;

import com.fssa.betterme.dao.EventDao;
import com.fssa.betterme.dao.UserDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.UserDAOException;
import com.fssa.betterme.exception.UserServiceException;
import com.fssa.betterme.exception.UserValidationException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.model.User;
import com.fssa.betterme.validation.UserValidator;

public class UserService {

	   public static boolean addUser(User user) throws  UserValidationException, UserServiceException   {
	        try {
				if (UserValidator.isValidUser(user)) {
					if(!UserDao.doesUserExist(user.getEmail())) {

				    UserDao.addUser(user);
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
					if(UserDao.doesUserExist(user.getEmail())) {

				    UserDao.updateUser(user);
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
					if(UserDao.doesUserExist(user.getEmail())) {

				    UserDao.deleteUser(user);
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
	   
	    public static List<User> getActiveUsers() throws UserServiceException {
	        try {
	                      
	            return UserDao.readActiveUsers(); 
	        } catch (UserDAOException e) {
	        	throw new UserServiceException(e.getMessage());
	        }
	    }
	    
	    public static List<User> getAllUsers() throws UserServiceException {
	        try {
	                      
	            return UserDao.readAllUsers(); 
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
	    		if(UserDao.doesUserExist(email)) {

	    			return  UserDao.getUserByEmail(email);
	    		}else {
	    			throw new UserValidationException("User doesn't exists");
	    		}
	    	} catch ( UserDAOException e) {
				throw new UserServiceException(e.getMessage());
			}
			
	    }
}

