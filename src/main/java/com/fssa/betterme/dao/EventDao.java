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
import com.fssa.betterme.model.Event;

public class EventDao {


	static final String EVENTNAME_TAB = "event_name";
	static final String EVENTADDR_TAB = "event_address";
	static final String PRICEVALUE_TAB = "price";
	static final String IMG_TAB = "img_url";
	static final String DES_TAB = "event_description";
	static final String ID_TAB = "id";
	static final String DATE_TAB = "date";
	static final String TIME_TAB = "time";
	static final String STATUS_TAB = "status";

	// adding new row to the table
	public static boolean addEvent(Event event, int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// Retrieve host ID based on host name

			String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price, host_id,img_url) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, event.getEventName());
				pst.setString(2, event.getEventDescription());
				pst.setString(3, event.getEventAddress());
				pst.setDate(4, Date.valueOf(event.getEventDate()));
				pst.setTime(5, Time.valueOf(event.getEventTime()));
				pst.setDouble(6, event.getPrice());
				pst.setInt(7, id);
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

	// This method checks if an event with the given name already exists in the
	// database.
	public static boolean doesEventExist(String eventName) throws DAOException {
		String query = "SELECT COUNT(*) FROM events WHERE event_name = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, eventName);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count == 1; // If count ==1 , event exists; otherwise, it doesn't.
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return false; // Default return value if an error occurs.
	}

	public boolean updateEvent(Event event) throws DAOException {

		String query = "UPDATE events SET event_description = ?, event_address = ?,date = ?,time = ?,price = ? WHERE id = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				int eventId = event.getId();

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

	public static boolean deleteEvent(Event event) throws DAOException {

		String queryDeleteEvents = "UPDATE events SET status =0   WHERE id = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(queryDeleteEvents)) {

				pst.setInt(1, event.getId());

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

	public static Event getEventsByName(String name) throws DAOException {
		Event events = null;

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events where event_name = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, name);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					events = createEvent(rs);

				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;

	}

	public static List<Event> readActiveEvents() throws DAOException {
		List<Event> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE status = 1";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					Event event = createEvent(rs);
					events.add(event);

				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Event> readAllEvents() throws DAOException {
		List<Event> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events ";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					Event event = createEvent(rs);
					events.add(event);

				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Event> getActiveEventByDate(LocalDate date) throws DAOException {
		List<Event> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE date = ? AND status =1";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setDate(1, Date.valueOf(date));

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					Event event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Event> getAllEventByDate(LocalDate date) throws DAOException {
		List<Event> events = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE date = ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setDate(1, Date.valueOf(date));

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					Event event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return events;
	}

	public static List<Event> activeEventRange(LocalDate start, LocalDate end) throws DAOException {
		List<Event> events = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE date BETWEEN ? AND ? AND status =1";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, String.valueOf(start));
				pst.setString(2, String.valueOf(end));
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					Event event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);

				return events;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public static List<Event> allEventRange(LocalDate start, LocalDate end) throws DAOException {
		List<Event> events = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE date BETWEEN ? AND ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, String.valueOf(start));
				pst.setString(2, String.valueOf(end));
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					Event event = createEvent(rs);
					events.add(event);

				}

				ConnectionUtil.close(con, pst, rs);

				return events;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public static Event getEventByID(int id) throws DAOException {
		Event events = null;
		String queryDeleteEvents = "SELECT id, event_name, event_description, event_address,img_url,date, time, price,status FROM events WHERE id = ? ";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(queryDeleteEvents)) {

				pst.setInt(1, id);

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					events = createEvent(rs);

				}

				ConnectionUtil.close(con, pst, null);

				return events;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	static Event createEvent(ResultSet rs) throws SQLException {
		int eventId = rs.getInt(ID_TAB);
		String eventName = rs.getString(EVENTNAME_TAB);
		String eventAddress = rs.getString(EVENTADDR_TAB);
		String eventDescription = rs.getString(DES_TAB);
		String imgURL = rs.getString(IMG_TAB);

		Date eventDate = rs.getDate(DATE_TAB);
		Time eventTime = rs.getTime(TIME_TAB);
		double price = rs.getDouble(PRICEVALUE_TAB);
		boolean isActive = rs.getBoolean(STATUS_TAB);

		Event event = new Event(eventId, eventName, eventDescription, eventAddress, imgURL, eventDate.toLocalDate(),
				eventTime.toLocalTime(), price, isActive);
		return event;

	}

}
