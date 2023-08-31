package com.fssa.betterme.service;

import java.util.List;


import com.fssa.betterme.dao.HostDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.service.message.Constants;
import com.fssa.betterme.validation.EventHostValidator;

/**
 * Provides services related to event hosts.
 */
public class EventHostService {

    /**
     * Adds an event host to the database.
     *
     * @param host The event host to be added.
     * @return True if the host was added successfully, false otherwise.
     * @throws ServiceException 
     * @throws DAOException If there's an issue with the data access.
     * @throws ValidationException 
     */
    public static boolean addHost(EventHost host) throws ServiceException, ValidationException  {
        try {
			if (EventHostValidator.isValidEventHost(host)) {
			    HostDao.addHost(host); 
			    return true;
			}
		} catch (  DAOException e) {
			throw new ServiceException(e.getMessage());
		}
        return false;
    }

    /**
     * Updates an event host in the database.
     *
     * @param host The event host to be updated.
     * @return True if the host was updated successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws ValidationException 
     * @throws ServiceException 
     */
    public static boolean updateHost(EventHost host) throws  ValidationException, ServiceException {
        if (EventHostValidator.isValidEventHost(host)) {
        	
        	
			try {
				EventHost eventHost = HostDao.findHostByEmail(host.getEmail());
				if (eventHost.getId() == 0) {
					throw new ValidationException(Constants.INVALIDHOST);
				}
	        	host.setId(eventHost.getId());
	            
	            HostDao.updateHost(host);
	            return true;
	        }
	      
			 catch (DAOException e) {			
				 throw new ServiceException(e.getMessage());
			}
        }
			  return false;
        	
    }

    /**
     * Deletes an event host from the database.
     *
     * @param host The event host to be deleted.
     * @return True if the host was deleted successfully, false otherwise.
     * @throws ValidationException 
     * @throws ServiceException 
     * @throws DAOException If there's an issue with the data access.
     */
    public static boolean deleteHost(EventHost host) throws ValidationException, ServiceException   {
        if (EventHostValidator.isValidEventHost(host)) {
        	 try {
        	EventHost EventHost = HostDao.findHostByEmail(host.getEmail()) ;
        	if (EventHost.getId() == 0) {
				throw new ValidationException(Constants.INVALIDHOST);
			}
           
				HostDao.deleteHostByHostId(EventHost.getId());
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
     * @throws ValidationException 
     * @throws ServiceException 
     */
    public static EventHost readHostByEmail(String email) throws  ValidationException, ServiceException {
    	EventHost value;
		try {
			value = HostDao.findHostByEmail(email);
			if(value == null) {
	    		throw new ValidationException(Constants.INVALIDHOSTEMAIL);
	    	}
	    	
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
     * @throws ValidationException 
     * @throws ServiceException 
     */
    
    public static EventHost readHostById( int id) throws  ValidationException, ServiceException {
    	try{
    		EventHost value =HostDao.findHostById(id);
    	
    	if(value == null) {
    		throw new ValidationException(Constants.INVALIDHOSTID);
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
    public static List<EventHost> readAllHost() throws DAOException {
    	List<EventHost> value = HostDao.readAllHost();
    
    	return value;
       
    	
    }
    


    

}
