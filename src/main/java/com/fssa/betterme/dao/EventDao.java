package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.EventValidationException;
import com.fssa.betterme.exception.ServiceException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.model.Trainner;
import com.fssa.betterme.service.EventHostService;
import com.fssa.betterme.util.ConnectionUtil;

public class EventDao {


	static final String EVENTNAME_TAB = "event_name";
	static final String EVENTADDR_TAB = "event_address";
	static final String PRICEVALUE_TAB = "price";
	static final String IMG_TAB = "images";
	static final String DES_TAB = "event_description";
	static final String ID_TAB = "id";
	static final String DATE_TAB = "date";
	static final String TIME_TAB = "time";
	static final String STATUS_TAB = "status";
	static final String EVENTABT_TAB = "short_intro";
 
	// adding new row to the table
	public static boolean addEvent(Event event, int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// Retrieve host ID based on host name

			String query = "INSERT INTO events (event_name, short_intro, event_description, date, time, event_address, images, price, trainers_id ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
			try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, event.getEventName());
			pst.setString(2, event.getEventAbout());
			pst.setString(3, event.getEventDescription());
			pst.setDate(4, Date.valueOf(event.getEventDate()));
			pst.setTime(5, Time.valueOf(event.getEventTime()));
			pst.setString(6, event.getEventAddress());
			pst.setString(7, event.getImageUrl());
			pst.setDouble(8, event.getPrice());
			pst.setInt(9, id);
			
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
	public static boolean doesEventExist(Event event) throws DAOException {
		String query = "SELECT COUNT(*) FROM events WHERE event_name = ? AND date = ?"; 

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, event.getEventName());
			pst.setDate(2, Date.valueOf(event.getEventDate()));
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
	


	public boolean updateEvent(Event event , int trainerId) throws DAOException {

		String query = "UPDATE events SET event_description = ?, event_address = ?,date = ?,time = ?,price = ? ,short_intro =?, trainers_id= ?,images=? WHERE id = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				int eventId = event.getId();

				pst.setInt(9, eventId);
				pst.setString(8, event.getImageUrl());
				pst.setInt(7, trainerId);
				pst.setString(6, event.getEventAbout());
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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status ,trainers_idFROM events where event_name = ?";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status,trainers_id FROM events WHERE status = 1";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status ,trainers_id FROM events ";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status, trainers_id FROM events WHERE date = ? AND status =1";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status, trainers_id FROM events WHERE date = ? ";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status, trainers_id FROM events WHERE date BETWEEN ? AND ? AND status =1";

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
			String query = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status, trainers_id FROM events WHERE date BETWEEN ? AND ? ";

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
		String queryDeleteEvents = "SELECT id, event_name, short_intro, event_description, date, time, event_address, images, price, status, trainers_id FROM events WHERE id = ? ";
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
		String eventAbout = rs.getString(EVENTABT_TAB);
		String eventDescription = rs.getString(DES_TAB);
		String Img_url = rs.getString(IMG_TAB);
		Date eventDate = rs.getDate(DATE_TAB);
		Time eventTime = rs.getTime(TIME_TAB);
		double price = rs.getDouble(PRICEVALUE_TAB);
		boolean isActive = rs.getBoolean(STATUS_TAB);
		int trainnerId = rs.getInt("trainers_id");
		Trainner trainner = null;
		try {
			trainner = EventHostService.findTrainerById(trainnerId);
		} catch (EventValidationException | ServiceException e) {
			
		}
		
		
		return new Event( eventId,  eventName,  eventAbout,  eventDescription,  eventAddress,
				 eventDate.toLocalDate(),  eventTime.toLocalTime(),  price, Img_url ,isActive, trainner);
	

	}
	


}
