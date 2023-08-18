package com.fssa.betterme.server;


import java.time.LocalDate;
import java.util.List;

import com.fssa.betterme.dao.EventDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.logger.Logger;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.EventValidator;

 public class EventService {
	
	



	public boolean addEvent(Events event)throws DAOException{
		if(EventValidator.isValidEvent(event)) {
			EventDao.addEvent(event);
		}
		return true;
	}
	public boolean updateEvent(Events event)throws DAOException{
		if(EventValidator.isValidEvent(event)) {
	
			
			EventDao eventDao = new EventDao();
			eventDao.updateEvent(event.getEventName(), "event_name", event.getEventName() +"loss");
			
		}
		return true;
	}
	public boolean deleteEvent(Events event)throws DAOException{
		
		if(EventValidator.isValidEvent(event)) {
			
			
			EventDao.deleteEvent(event);
		}
		return true;
	}
	
	public boolean getEvents()throws DAOException {
	
		List<Events> values=EventDao.readEvents();
		printEvents(values);
		List<Events> values1=EventDao.getEventByDate();
		printEvents(values1);
		List<Events> values2=EventDao.eventRange(LocalDate.of(2023,11, 20), LocalDate.of(2023,11, 25));
		printEvents(values2);
		
		return true;
	}

	void printEvents(List<Events> val) {
		Logger log = new Logger();
		for (Events events : val) {
			log.info(events);
		}
	}


}



