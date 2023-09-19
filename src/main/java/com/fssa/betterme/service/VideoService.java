package com.fssa.betterme.service;

import com.fssa.betterme.dao.BookingDao;
import com.fssa.betterme.dao.RecordedVideoDao;
import com.fssa.betterme.exception.BookDAOException;

public class VideoService {
	
	//TODO :: add date as unique
	public static boolean AddVideo (int trainnerId , int userId, String video) throws BookDAOException {
		RecordedVideoDao.addVideo(trainnerId, userId, video);
			
			  
		return false;
		
	}
}
