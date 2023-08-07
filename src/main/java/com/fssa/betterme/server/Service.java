package com.fssa.betterme.server;

import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fssa.betterme.objects.*;
import com.fssa.betterme.validation.EventValidator;
import com.fssa.betterme.dao.DAOException;
import com.fssa.betterme.dao.EventDao;

public class Service {
	private Events eventValidation;
	private EventValidator EventValidator;
	public Service(Events eventValidation,EventValidator EventValidator) {
		super();
		this.eventValidation = eventValidation;
		this.EventValidator = EventValidator;
	}
	public Service() {
		
	}
	public boolean addProduct(Events event)throws DAOException,SQLException{
		if(EventValidator.isValidEvent(event)) {
			EventDao.addEvent(event);
		}
		return true;
	}
	public boolean updateEvent(Events event)throws DAOException,SQLException{
		if(EventValidator.isValidEvent(event)) {
			int num  =1;
			EventDao.updateEvent(num,event.getEventName());
			EventDao.updateEvent(num,event.getPrice());
		}
		return true;
	}
	public boolean deleteProduct(Events event)throws DAOException,SQLException{
		
		if(EventValidator.isValidEvent(event)) {
			int EventId =1;
			EventDao.deleteEvent(EventId);
		}
		return true;
	}
	public boolean getEvents()throws DAOException,SQLException {
		EventDao.readEvent();
		return true;
	}
}



