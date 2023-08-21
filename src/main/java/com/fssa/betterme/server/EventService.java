package com.fssa.betterme.server;

import java.time.LocalDate;
import java.util.List;

import com.fssa.betterme.dao.EventDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.logger.Logger;
import com.fssa.betterme.objects.Events;
import com.fssa.betterme.validation.EventValidator;

/**
 * Provides services related to events.
 */
public class EventService {

    /**
     * Adds an event to the database.
     *
     * @param event The event to be added.
     * @return True if the event was added successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean addEvent(Events event) throws DAOException {
        if (EventValidator.isValidEvent(event)) {
            EventDao.addEvent(event);
            return true;
        }
        return false;
    }

    /**
     * Updates an event in the database.
     *
     * @param event The event to be updated.
     * @return True if the event was updated successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean updateEvent(Events event) throws DAOException {
        if (EventValidator.isValidEvent(event)) {
            EventDao eventDao = new EventDao();
            eventDao.updateEvent(event.getEventName(), "event_name", event.getEventName() + "loss");
            return true;
        }
        return false;
    }

    /**
     * Deletes an event from the database.
     *
     * @param event The event to be deleted.
     * @return True if the event was deleted successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean deleteEvent(Events event) throws DAOException {
        if (EventValidator.isValidEvent(event)) {
            EventDao.deleteEvent(event);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all events from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean getEvents() throws DAOException {
        List<Events> values = EventDao.readEvents();
        printEvents(values);
        return true;
    }

    /**
     * Retrieves events by a specific date from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean getEventByDate() throws DAOException {
        List<Events> values1 = EventDao.getEventByDate(LocalDate.of(2023, 8, 19));
        printEvents(values1);
        return true;
    }

    /**
     * Retrieves events within a date range from the database.
     *
     * @return True if the events were retrieved successfully, false otherwise.
     * @throws DAOException If there's an issue with the data access.
     */
    public boolean getEventByRange() throws DAOException {
        List<Events> values2 = EventDao.eventRange(LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 25));
        printEvents(values2);
        return true;
    }

    /**
     * Prints the events to the logger.
     *
     * @param val The list of events to be printed.
     */
    void printEvents(List<Events> val) {
        Logger log = new Logger();
        for (Events events : val) {
            log.info(events.toString());
        }
    }
}
