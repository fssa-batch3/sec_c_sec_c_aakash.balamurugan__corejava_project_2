package com.fssa.betterme.server;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fssa.betterme.objects.*;
import com.fssa.betterme.validation.EventValidator;
import com.fssa.betterme.dao.EventDao;

public class Service {
public static void main(String[] args) {
	EventHost host = new EventHost("aakash", "9876543210", "aakash@gmail.com");
	
	Events myEvent = new Events("prasana event",
			"This is a very big event so the description should contain atleast of 30 characters",
			"This is a very big event so the Address should be in breief with 30 and more characters",
			LocalDate.of(2023, 10, 1), LocalTime.of(9, 00), 400, host);
	try {
		if(EventValidator.isValidEvent(myEvent)) {
		
//			EventDao.addEvents(myEvent.getEventName(), myEvent.getEventDescription(), myEvent.getEventAddress(), myEvent.getEventDate(), myEvent.getEventTime(), myEvent.getPrice());
//			EventDao.GetEventByDate();
//			EventDao.readEvent();
			EventDao.EventRange(LocalDate.of(2023, 10, 01), LocalDate.of(2020, 10, 31));
		}
	} catch (IllegalArgumentException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}


