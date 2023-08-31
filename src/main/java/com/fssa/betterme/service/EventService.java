package com.fssa.betterme.service;



import java.time.LocalDate;

import java.util.List;



import com.fssa.betterme.dao.*;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.service.message.Constants;
import com.fssa.betterme.validation.EventValidator;

/**
 * Provides services related to events.
 */
public class EventService {

    /**
     * Adds an event to the database.
     *
     * @param event The event to be added.
     * @return True if the event was added successfully, false otherwise.
     * @throws ValidationException 
     * @throws ServiceException 
     */
    public static boolean addEvent(Event event) throws ValidationException, ServiceException  {
        try {
			if (EventValidator.isValidEvent(event)) {
				if(!EventDao.doesEventExist(event.getEventName())) {
					
				
				EventHost host = HostDao.findHostByEmail(event.getHost().getEmail()) ;
				if (host.getId() == 0) {
					throw new ValidationException(Constants.INVALIDHOST);
				}
			    EventDao.addEvent(event,host.getId());
			    return true;
			    }else {
			    	throw new ValidationException(Constants.EVENTALREADYEXISTS);
			    }
			}
		} catch ( DAOException e) {
			throw new ServiceException(e.getMessage());
		}
        return false;
    }

    /**
     * Updates an event in the database.
     *
     * @param event The event to be updated.
     * @return True if the event was updated successfully, false otherwise.
     * @throws ServiceException 
     * @throws ValidationException 
     */
    public static boolean updateEvent(Event event) throws ServiceException, ValidationException{
        try {
        	if(EventDao.doesEventExist(event.getEventName())) {
        		if (EventValidator.isValidEvent(event)) {
        			EventDao eventDao = new EventDao();
			    
			    	eventDao.updateEvent(event);
			    	return true;
				}
        	}else {
        		throw new ValidationException(Constants.EVENTDOESNTEXISTS);
        	}
		} catch ( DAOException e) {
			throw new ServiceException(e.getMessage());
		}
        return false;
    }

    /**
     * Deletes an event from the database.
     *
     * @param event The event to be deleted.
     * @return True if the event was deleted successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws ValidationException 
     * @throws ServiceException 
     */
    public static boolean deleteEvent(Event event) throws ValidationException, ServiceException {
        try {
        	if(EventDao.doesEventExist(event.getEventName())) {
			if (EventValidator.isValidEvent(event)) {
			    EventDao.deleteEvent(event);
			    return true;
			}
        	}else {
        		throw new ValidationException(Constants.EVENTDOESNTEXISTS);
        	}
        } catch (DAOException e) {
        	throw new ServiceException(e.getMessage());
		}
        return false;
    }

    /**
     * Retrieves all active events from the database.
     *
     * @return A list of active events if retrieved successfully, an empty list if no active events, or null if an error occurs.
     * @throws ServiceException 
     */
    public static List<Event> getActiveEvents() throws ServiceException {
        try {
            List<Event> activeEvents = EventDao.readActiveEvents();            
            return activeEvents; 
        } catch (DAOException e) {
        	throw new ServiceException(e.getMessage());
        }
    }


    /**
     * Retrieves  events with given eventName from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     * @throws ValidationException 
     */
    public static Event getEventByEventName(Event event) throws ServiceException, ValidationException {
        Event values = null;
		try {
			values = EventDao.getEventsByName(event.getEventName());
			 if(values == null )
		        	throw new ValidationException(Constants.INVALIDEVENTNAME);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
       
      
        return values;
    }

    /**
     * Retrieves events by a specific date from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
  
     */
    public static List<Event> getActiveEventByDate(LocalDate date) throws ServiceException  {
        List<Event> values = null;
		try {
			values = EventDao.getActiveEventByDate(date);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	
        return values;
    }

    /**
     * Retrieves events within a date range from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException  

     */
    public static List<Event> getActiveEventByRange(LocalDate start, LocalDate end) throws ServiceException {
        List<Event> values = null;
		try {
			values = EventDao.activeEventRange(start, end);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	
        
      
        return values;
    }
    
    
    /**
     * Retrieves all events from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     */
    public static List<Event> getAllEvents() throws ServiceException  {
    	 List<Event> values = null;
    	try {
        values = EventDao.readAllEvents();
      
        
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    	return values;
    	
    }

    /**
     * Retrieves events by a specific date from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     */
    public static List<Event> getAllEventByDate(LocalDate date) throws ServiceException {
    	try {
        List<Event> values = EventDao.getAllEventByDate(date);
        
        return values;
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    }

    /**
     * Retrieves events within a date range from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     */
    public static List<Event> getAllEventByRange(LocalDate start, LocalDate end) throws ServiceException {
    	try {
        List<Event> values = EventDao.allEventRange(start, end);
      
        return values;
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    }
    
    
    /**
     * Retrieves events within a date range from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     */
    public static Event getEventById(int id) throws ServiceException{
    	try {
        Event values = EventDao.getEventByID(id);
        
        return  values;
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    }


}
