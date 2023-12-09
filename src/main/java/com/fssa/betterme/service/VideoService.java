package com.fssa.betterme.service;

import java.util.List;

import com.fssa.betterme.dao.BookingDao;
import com.fssa.betterme.dao.RecordedVideoDao;
import com.fssa.betterme.exception.BookDAOException;
import com.fssa.betterme.exception.DAOException;

public class VideoService {
	
	static RecordedVideoDao ser = new RecordedVideoDao();
	
	//TODO :: add date as unique
	public static boolean AddVideo (int trainnerId , int userId, String video) throws  DAOException {
		return ser.addVideo(trainnerId, userId, video);
			
			  
	
		
	}
	
	public static List<String> getVideoByUser ( int userId) throws  DAOException {
		return ser.getVideoByUser( userId);
			
			  
	
		
	}
}
