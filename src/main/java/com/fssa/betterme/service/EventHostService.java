package com.fssa.betterme.service;

import java.util.List;


import com.fssa.betterme.dao.HostDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.EventValidationException;
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
     * @throws EventValidationException 
     */
    public static boolean addHost(EventHost host) throws ServiceException, EventValidationException  {
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
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    public static boolean updateHost(EventHost host) throws  EventValidationException, ServiceException {
        if (EventHostValidator.isValidEventHost(host)) {
        	
        	
			try {
				EventHost eventHost = HostDao.findHostByEmail(host.getEmail());
				if (eventHost.getId() == 0) {
					throw new EventValidationException(Constants.INVALIDHOST);
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
     * @throws EventValidationException 
     * @throws ServiceException 
     * @throws DAOException If there's an issue with the data access.
     */
    public static boolean deleteHost(EventHost host) throws EventValidationException, ServiceException   {
        if (EventHostValidator.isValidEventHost(host)) {
        	 try {
        	EventHost eventHost = HostDao.findHostByEmail(host.getEmail()) ;
        	if (eventHost.getId() == 0) {
				throw new EventValidationException(Constants.INVALIDHOST);
			}
           
				HostDao.deleteHostByHostId(eventHost.getId());
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
    public static EventHost readHostByEmail(String email) throws  EventValidationException, ServiceException {
    	EventHost value;
		try {
			value = HostDao.findHostByEmail(email);
			if(value == null) {
	    		throw new EventValidationException(Constants.INVALIDHOSTEMAIL);
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
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    
    public static EventHost readHostById( int id) throws  EventValidationException, ServiceException {
    	try{
    		EventHost value =HostDao.findHostById(id);
    	
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
    public static List<EventHost> readAllHost() throws DAOException {
    	List<EventHost> value = HostDao.readAllHost();
    
    	return value;
       
    	
    }
    


    

}
