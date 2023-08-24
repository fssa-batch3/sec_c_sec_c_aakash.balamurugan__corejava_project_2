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
	static final String eventName = "event_name";
	static final String eventAddressStr = "event_address";
	static final String priceValue = "price";

	// adding new row to the table
	public static boolean addEvent(Events event, int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// Retrieve host ID based on host name
			int hostId = findHostId(event.getHost().getHostName() );
			if (hostId == -1) {
				throw new DAOException("host not found");
			}
			String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price, host_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, event.getEventName());
				pst.setString(2, event.getEventDescription());
				pst.setString(3, event.getEventAddress());
				pst.setDate(4, Date.valueOf(event.getEventDate()));
				pst.setTime(5, Time.valueOf(event.getEventTime()));
				pst.setDouble(6, event.getPrice());
			    pst.setInt(7, hostId);

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

	public static int findHostId(String hostName) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
		String query = " SELECT id FROM hosts WHERE host_name = ?";
		int hostId = -1;
		try (PreparedStatement pst = con.prepareStatement(query);) {

			pst.setString(1, hostName);

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

					int eventId = rs.getInt("id");
					String eventName = rs.getString("event_name");
					String eventAddress = rs.getString(eventAddressStr);
					String eventDescription = rs.getString("event_description");
					Date eventDate = rs.getDate("date");
					Time eventTime = rs.getTime("time");
					double price = rs.getDouble(priceValue);

					Events event = new Events(eventId, eventName, eventDescription, eventAddress,
							eventDate.toLocalDate(), eventTime.toLocalTime(), price);
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

					int eventId = rs.getInt("id");
					String eventName = rs.getString("event_name");
					String eventAddress = rs.getString(eventAddressStr);
					String eventDescription = rs.getString("event_description");
					Date eventDate = rs.getDate("date");
					Time eventTime = rs.getTime("time");
					double price = rs.getDouble(priceValue);

					Events event = new Events(eventId, eventName, eventDescription, eventAddress,
							eventDate.toLocalDate(), eventTime.toLocalTime(), price);
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

					int eventId = rs.getInt("id");
					String eventName = rs.getString("event_name");
					String eventAddress = rs.getString(eventAddressStr);
					String eventDescription = rs.getString("event_description");
					Date eventDate = rs.getDate("date");
					Time eventTime = rs.getTime("time");
					double price = rs.getDouble(priceValue);

					Events event = new Events(eventId, eventName, eventDescription, eventAddress,
							eventDate.toLocalDate(), eventTime.toLocalTime(), price);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);

				return events;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

}
