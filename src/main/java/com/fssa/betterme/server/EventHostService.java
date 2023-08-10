package com.fssa.betterme.server;



import com.fssa.betterme.dao.HostDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.objects.EventHost;

import com.fssa.betterme.validation.EventHostValidator;

public class EventHostService {

	
	public static boolean addHost(EventHost host)throws DAOException{
		if(EventHostValidator.isValidEventHost(host)) {
			HostDao.addHost(host);
		}
		return true;
	}
	public static boolean updateHost(EventHost host)throws DAOException{
		if(EventHostValidator.isValidEventHost(host)) {
	
			
			HostDao hostDao = new HostDao();
			hostDao.updateHost(host);
			
		}
		return true;
	}
	public static boolean deleteHost(EventHost host)throws DAOException{
		
		if(EventHostValidator.isValidEventHost(host)) {
			
			
			HostDao.deleteHostByHostName(host.getHostName());
		}
		return true;
	}
	
	public static boolean readAllHost()throws DAOException{
		
			HostDao.readAllHost();
			
		
		return true;
	}



	
	
	
}
