package com.fssa.betterme.service;



import java.time.LocalDate;

import java.util.List;

import com.fssa.betterme.dao.EventDao;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.model.Trainer;
import com.fssa.betterme.service.message.Constants;
import com.fssa.betterme.validation.EventValidator;

/**
 * Provides services related to events.
 */
public class EventService {

	static EventDao ser = new EventDao();
	static EventValidator val = new EventValidator();
	static TrainerService trainerSer = new TrainerService();
     /**
     * Adds an event to the database.
     *
     * @param event The event to be added.
     * @return True if the event was added successfully, false otherwise.
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    public boolean addEvent(Event event) throws EventValidationException, ServiceException  {
        try { 
			if (val.isValidEvent(event)) {
				if(!ser.doesEventExist(event)) {
					
				
				Trainer host = trainerSer.findTrainerByEmail(event.getTrainner().getEmail()) ;
				if (host.getId() == 0) {
					throw new EventValidationException(Constants.INVALIDHOST);
				}
			    ser.addEvent(event,host.getId());
			    return true;
			    }else {
			    	throw new EventValidationException(Constants.EVENTALREADYEXISTS);
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
     * @throws EventValidationException 
     */
    public boolean updateEvent(Event event) throws ServiceException, EventValidationException{
        try {
        	if(ser.doesEventExist(event)) {
        		if (val.isValidEvent(event)) {
        			EventDao eventDao = new EventDao();
			     
        			
    				Trainer host = trainerSer.findTrainerByEmail(event.getTrainner().getEmail()) ;
    				if (host.getId() == 0) {
    					throw new EventValidationException(Constants.INVALIDHOST);
    				}
			    	eventDao.updateEvent(event, host.getId());
			    	return true;
				}
        	}else {
        		throw new EventValidationException(Constants.EVENTDOESNTEXISTS);
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
     * @throws EventValidationException 
     * @throws ServiceException 
     */
    public  boolean deleteEvent(Event event) throws EventValidationException, ServiceException {
        try {
        	if(ser.doesEventExist(event)) {
			if (val.isValidEvent(event)) {
				ser.deleteEvent(event);
			    return true;
			}
        	}else {
        		throw new EventValidationException(Constants.EVENTDOESNTEXISTS);
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
    public  List<Event> getActiveEvents() throws ServiceException {
        try {
                     
            return ser.readActiveEvents();
        } catch (DAOException e) {
        	throw new ServiceException(e.getMessage());
        }
    }


    /**
     * Retrieves  events with given eventName from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     * @throws EventValidationException 
     */
    public  Event getEventByEventName(Event event) throws ServiceException, EventValidationException {
        Event values = null;
		try {
			values = ser.getEventsByName(event.getEventName());
			 if(values == null )
		        	throw new EventValidationException(Constants.INVALIDEVENTNAME);
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
    public  List<Event> getActiveEventByDate(LocalDate date) throws ServiceException  {
        List<Event> values = null;
		try {
			values = ser.getActiveEventByDate(date);
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
    public  List<Event> getActiveEventByRange(LocalDate start, LocalDate end) throws ServiceException {
        List<Event> values = null;
		try {
			values = ser.activeEventRange(start, end);
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
    public  List<Event> getAllEvents() throws ServiceException  {
   
    	try {
        return ser.readAllEvents();
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    	
    	
    }

    /**
     * Retrieves events by a specific date from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws ServiceException 
     */
    public  List<Event> getAllEventByDate(LocalDate date) throws ServiceException {
    	try {
        return ser.getAllEventByDate(date);
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
    public  List<Event> getAllEventByRange(LocalDate start, LocalDate end) throws ServiceException {
    	try {
        return ser.allEventRange(start, end);
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
    public  Event getEventById(int id) throws ServiceException{
    	try {
    		return ser.getEventByID(id);
        
       
    	} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
    }
    



}
