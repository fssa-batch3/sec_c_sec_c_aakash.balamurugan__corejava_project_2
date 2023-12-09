package com.fssa.betterme.service;

import java.util.List;

import com.fssa.betterme.dao.BookingDao;
import com.fssa.betterme.exception.BookDAOException;
import com.fssa.betterme.model.Event;

public class BookingService {
	
	static final short MAXCOUNT = 10;
	static BookingDao ser = new BookingDao();
	
	public static boolean AddBooking (int eventId , int userId) throws BookDAOException {
		if(!ser.bookingExists(eventId , userId) && ser.getBookingCount(eventId) <  MAXCOUNT) {
			if(ser.createBooking(eventId, userId))
				return true;
		}
			
			
		return false;
		
	}
	
	public static boolean DeleteBooking (int eventId , int userId) throws BookDAOException {
		if(ser.bookingExists(eventId , userId)) {
			if(ser.deleteBooking(eventId, userId))
				return true;
		}
			
			
		return false;
		
	}
	
	public static List<Event> getBookingForUser ( int userId) throws BookDAOException {
		if(userId !=0)
		return ser.getEventForUser(userId);
		
		
		return null;
			

		
	}
	
	
}
