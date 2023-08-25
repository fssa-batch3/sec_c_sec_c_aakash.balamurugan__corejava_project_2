package com.fssa.betterme.dao;

import java.sql.Connection;

import com.fssa.betterme.util.ConnectionUtil;
import com.fssa.betterme.util.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Time;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.model.Events;

public class EventDao {

	
	static Logger log = new Logger();
	static final String EVENTNAME_TAB = "event_name";
	static final String EVENTADDR_TAB = "event_address";
	static final String PRICEVALUE_TAB = "price";
	static final String IMA_TAB = "img_url";
	static final String DES_TAB = "event_description";
	static final String ID_TAB = "id";
	static final String DATE_TAB = "date";
	static final String TIME_TAB = "time";
	static final String STATUS_TAB = "status";

	// adding new row to the table
	public static boolean addEvent(Events event, int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// Retrieve host ID based on host name
			int hostId = findHostId(event.getHost().getEmail());
			if (hostId == -1) {
				throw new DAOException("host not found");
			}
			String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price, host_id,img_url) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, event.getEventName());
				pst.setString(2, event.getEventDescription());
				pst.setString(3, event.getEventAddress());
				pst.setDate(4, Date.valueOf(event.getEventDate()));
				pst.setTime(5, Time.valueOf(event.getEventTime()));
				pst.setDouble(6, event.getPrice());
			    pst.setInt(7, hostId);
			    pst.setString(8, event.getImageURL());

				int rowsAffected = pst.executeUpdate();

				if (rowsAffected <= 0) {
					throw new DAOException("Failed to insert event.");
				}

				return true;

			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public static int findHostId(String email) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
		String query = " SELECT id FROM hosts WHERE email = ?";
		int hostId = -1;
		try (PreparedStatement pst = con.prepareStatement(query);) {

			pst.setString(1, email);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				hostId = rs.getInt("id");

			}
			ConnectionUtil.close(null, pst, rs);

		}
		return hostId;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public static int findEventId(String eventName, Connection con) throws DAOException {

		String query = " SELECT id FROM events WHERE event_name = ?";
		int eventId = -1;
		try (PreparedStatement pst = con.prepareStatement(query);) {

			pst.setString(1, eventName);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				eventId = rs.getInt("id");

			} 
			ConnectionUtil.close(null, pst, rs);

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return eventId;
	}

	public boolean updateEvent(Events event) throws DAOException {

		String query = "UPDATE events SET event_description = ?, event_address = ?,date = ?,time = ?,price = ? WHERE id = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				int eventId = findEventId(event.getEventName(), con);

			

				if (eventId == -1)
					throw new DAOException("Event Not found");

				pst.setInt(6, eventId);
				pst.setString(1, event.getEventDescription());
				pst.setString(2, event.getEventAddress());
				pst.setDate(3, Date.valueOf(event.getEventDate()));
				pst.setTime(4, Time.valueOf(event.getEventTime()));
				pst.setDouble(5, event.getPrice());

				int rows = pst.executeUpdate();
				ConnectionUtil.close(con, pst, null);
				if (rows <= 0) {
					throw new DAOException("Update event failed");
				}

				return true;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public static boolean deleteEvent(Events event) throws DAOException {

		String queryDeleteEvents = "DELETE FROM events WHERE id = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(queryDeleteEvents)) {

				int eventId = findEventId(event.getEventName(), con);

				if (eventId == -1)
					throw new DAOException("Event Not found");

				pst.setInt(1, eventId);
				

				int rows = pst.executeUpdate();

				if (rows <= 0) {
					throw new DAOException("Delete event failed");
				}

				ConnectionUtil.close(con, pst, null);

				return true;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	public static List<Events> readEvents() throws DAOException {
		List<Events> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM events";

			try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery();) {

				while (rs.next()) { 

				 Events event = createEvent(rs);
					events.add(event);

				} 
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Events> getEventByDate(LocalDate date) throws DAOException {
		List<Events> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM events WHERE date = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setDate(1, Date.valueOf(date));

				ResultSet rs = pst.executeQuery();

				while (rs.next()) { 
					Events event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Events> eventRange(LocalDate start, LocalDate end) throws DAOException {
		List<Events> events = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM events WHERE date BETWEEN ? AND ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, String.valueOf(start));
				pst.setString(2, String.valueOf(end));
				ResultSet rs = pst.executeQuery();

				while (rs.next()) { 

					Events event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);

				return events;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	static Events createEvent(ResultSet rs) throws SQLException{
		int eventId = rs.getInt(ID_TAB);
		String eventName = rs.getString(EVENTNAME_TAB);
		String eventAddress = rs.getString(EVENTADDR_TAB);
		String eventDescription = rs.getString(DES_TAB);
		String imgURL = rs.getString(IMA_TAB);
		
		Date eventDate = rs.getDate(DATE_TAB);
		Time eventTime = rs.getTime(TIME_TAB);
		double price = rs.getDouble(PRICEVALUE_TAB);
		boolean isActive = rs.getBoolean(STATUS_TAB); 

		Events event = new Events(eventId, eventName, eventDescription, eventAddress,imgURL,
				eventDate.toLocalDate(), eventTime.toLocalTime(), price,isActive);
		return event;
		
	}
	
	

}
