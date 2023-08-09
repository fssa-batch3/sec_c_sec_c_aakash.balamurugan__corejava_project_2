package com.fssa.betterme.server;

import java.sql.SQLException;
import java.time.LocalDate;

import com.fssa.betterme.objects.*;
import com.fssa.betterme.validation.EventValidator;
import com.fssa.betterme.dao.DAOException;
import com.fssa.betterme.dao.EventDao;

public class Service {
	
	



	public static boolean addEvent(Events event)throws DAOException,SQLException{
		if(EventValidator.isValidEvent(event)) {
			EventDao.addEvent(event);
		}
		return true;
	}
	public static boolean updateEvent(Events event)throws DAOException,SQLException{
		if(EventValidator.isValidEvent(event)) {
	
			
			EventDao eventDao = new EventDao();
			eventDao.updateEvent(event.getEventName(), "event_name", event.getEventName() +"loss");
			
		}
		return true;
	}
	public static boolean deleteEvent(Events event)throws DAOException,SQLException{
		
		if(EventValidator.isValidEvent(event)) {
			
			
			EventDao.deleteEvent(event);
		}
		return true;
	}
	public static boolean getEvents()throws SQLException, DAOException {
		EventDao.readEvent();
		EventDao.getEventByDate();
		EventDao.eventRange(LocalDate.of(2023,11, 20), LocalDate.of(2023,11, 25));
		
		return true;
	}



}



