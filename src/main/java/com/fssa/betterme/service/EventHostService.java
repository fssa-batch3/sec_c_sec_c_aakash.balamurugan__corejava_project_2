package com.fssa.betterme.service;

import java.util.List;


import com.fssa.betterme.dao.HostDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.util.Logger;
import com.fssa.betterme.validation.EventHostValidator;

/**
 * Provides services related to event hosts.
 */
public class EventHostService {

    /**
     * Adds an event host to the database.
     *
     * @param host The event host to be added.
     * @return True if the host was added successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws ValidationException 
     */
    public static boolean addHost(EventHost host) throws DAOException, ValidationException {
        if (EventHostValidator.isValidEventHost(host)) {
            HostDao.addHost(host);
            return true;
        }
        return false;
    }

    /**
     * Updates an event host in the database.
     *
     * @param host The event host to be updated.
     * @return True if the host was updated successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     * @throws ValidationException 
     */
    public static boolean updateHost(EventHost host) throws DAOException, ValidationException {
        if (EventHostValidator.isValidEventHost(host)) {
            HostDao hostDao = new HostDao();
            hostDao.updateHost(host);
            return true;
        }
        return false;
    }

    /**
     * Deletes an event host from the database.
     *
     * @param host The event host to be deleted.
     * @return True if the host was deleted successfully, false otherwise.
     * @throws ValidationException 
     * @throws DAOException If there's an issue with the data access.
     */
    public static boolean deleteHost(EventHost host) throws ValidationException, DAOException   {
        if (EventHostValidator.isValidEventHost(host)) {
            HostDao.deleteHostByHostName(host.getHostName());
            return true;
        }
        return false;
    }

    /**
     * Retrieves all event hosts from the database.
     *
     * @return True if the hosts were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public static boolean readAllHost() throws DAOException {
    	printHosts( HostDao.readAllHost());
        return true;
    }
    
    /**
     * Prints the hosts to the logger.
     *
     * @param val The list of events to be printed.
     */
    static void printHosts(List<EventHost> val) {
        Logger log = new Logger();
        for (EventHost hosts : val) {
            log.info(hosts.toString());
        }
    }
}
