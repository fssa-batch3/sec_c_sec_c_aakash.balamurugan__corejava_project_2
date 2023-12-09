package com.fssa.betterme.service;

import java.util.List;


import com.fssa.betterme.dao.TrainerDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.model.Trainer;
import com.fssa.betterme.service.message.Constants;
import com.fssa.betterme.validation.TrainerValidator;

/**
 * Provides services related to event hosts.
 */
public class TrainerService {
	
	TrainerValidator val = new TrainerValidator();
	TrainerDao dao = new TrainerDao();

    /**
     * Adds an event host to the database.
     *
     * @param host The event host to be added.
     * @return True if the host was added successfully, false otherwise.
     * @throws ServiceException 
     * @throws DAOException If there's an issue with the data access.
     * @throws EventValidationException 
     */
    public boolean addTrainer(Trainer host) throws ServiceException, EventValidationException  {
        try {
			if (val.isValidEventHost(host)) {
				Trainer eventHost = dao.findTrainerByEmail(host.getEmail()) ;
	        	if (eventHost.getId() != 0) {
				dao.addTrainer(host); 
			    return true;
	        	}
			}
		} catch (  DAOException e) {
			throw new ServiceException(e.getMessage());
		}
        return false;
    }


    /**
     * Deletes an event host from the database.
     *
     * @param host The event host to be deleted.
     * @return True if the host was deleted successfully, false otherwise.
     * @throws EventValidationException 
     * @throws ServiceException 
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean deleteHost(Trainer host) throws EventValidationException, ServiceException   {
        if (val.isValidEventHost(host)) {
        	 try {
        	Trainer eventHost = dao.findTrainerByEmail(host.getEmail()) ;
        	if (eventHost.getId() == 0) {
				throw new EventValidationException(Constants.INVALIDHOST);
			}
           
        	dao.deleteTrainerById(eventHost.getId());
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage());
			}
            return true;
        }
        return false;
    }


    
    /**
     * Retrieves all event hosts from the database.
     *
     * @return True if the hosts were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    public  Trainer findTrainerByEmail(String email) throws  EventValidationException, ServiceException {
    	Trainer value;
		try {
			value = dao.findTrainerByEmail(email);
			if(value == null) {
	    		throw new EventValidationException(Constants.INVALIDHOSTEMAIL);
	    	}
	    	
	    	return value;
		} catch (DAOException e) {
			
			throw new ServiceException(e.getMessage());
		}
    	
    	
    	
    }
    
    public  String getTrainnerPassByEmail(String email) throws  EventValidationException, ServiceException {
    	String value;
		try {
			value = dao.getTrainnerPassByEmail(email);
		
	    	
	    	return value;
		} catch (DAOException e) {
			
			throw new ServiceException(e.getMessage());
		}
    	
    	
    	
    }
    
    

    
    /**
     * Retrieves all event hosts from the database.
     *
     * @return the object if the hosts were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    
    public  Trainer findTrainerById( int id) throws  EventValidationException, ServiceException {
    	try{
    		Trainer value =dao.findTrainerById(id);
    	
    	if(value == null) {
    		throw new EventValidationException(Constants.INVALIDHOSTID);
    	}
    	
    	return value;
    }catch(DAOException e) {
    	throw new ServiceException(e.getMessage());
    }
    	
    }
    
    /**
     * Retrieves all event hosts from the database.
     *
     * @return True if the hosts were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public  List<Trainer> readAllTrainer() throws DAOException {
    	return dao.readAllTrainer();
    
    	
       
    	
    }
    



    

}
