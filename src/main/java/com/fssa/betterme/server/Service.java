package com.fssa.betterme.server;

import java.sql.SQLException;




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
			int num  =1;
			EventDao.updateEvent(num,event.getEventName());
			EventDao.updateEvent(num,event.getPrice());
		}
		return true;
	}
	public static boolean deleteProduct(Events event)throws DAOException,SQLException{
		
		if(EventValidator.isValidEvent(event)) {
			int eventId =1;
			EventDao.deleteEvent(eventId);
		}
		return true;
	}
	public static boolean getEvents()throws SQLException {
		EventDao.readEvent();
		EventDao.getEventByDate();
		
		return true;
	}



}



