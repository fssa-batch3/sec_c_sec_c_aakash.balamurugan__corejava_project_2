package com.fssa.betterme.service;

import java.util.List;

import com.fssa.betterme.dao.BookingDao;
import com.fssa.betterme.exception.BookDAOException;
import com.fssa.betterme.model.Event;

public class BookingService {
	
	public static boolean AddBooking (int eventId , int userId) throws BookDAOException {
		if(!BookingDao.BookingExists(eventId , userId)) {
			if(BookingDao.createBooking(eventId, userId))
				return true;
		}
			
			
		return false;
		
	}
	
	public static boolean DeleteBooking (int eventId , int userId) throws BookDAOException {
		if(BookingDao.BookingExists(eventId , userId)) {
			if(BookingDao.DeleteBooking(eventId, userId))
				return true;
		}
			
			
		return false;
		
	}
	
	public static List<Event> getBookingForUser ( int userId) throws BookDAOException {
		if(userId !=0)
		return BookingDao.getEventForUser(userId);
		
		
		return null;
			

		
	}
	
	
}
