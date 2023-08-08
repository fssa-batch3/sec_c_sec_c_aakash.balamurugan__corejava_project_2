package com.fssa.betterme.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fssa.betterme.objects.Events;

public class EventDao {

	// adding new row to the table
	public static boolean addEvent(Events event) throws SQLException, DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price) VALUES (?, ?, ?, ?, ?, ?);";
			try (PreparedStatement pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pst.setString(1, event.getEventName());
				pst.setString(2, event.getEventDescription());
				pst.setString(3, event.getEventAddress());
				pst.setDate(4, Date.valueOf(event.getEventDate()));
				pst.setTime(5, Time.valueOf(event.getEventTime()));
				pst.setDouble(6, event.getPrice());

				int rows = pst.executeUpdate();

				if (rows <= 0) {
					throw new DAOException("Failed to insert event.");
				}

				// Get the auto-generated event ID
				int eventId = -1;
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						eventId = generatedKeys.getInt(1);
					} else {
						throw new DAOException("Failed to get the auto-generated event ID.");
					}
				}

				// Find the host ID
				int hostId = findHostId(event.getHostName());

				// Associate the host with the event in the join table
				if (hostId != -1) {
					joinHostEvent(eventId, hostId);
				} else {
					throw new DAOException("Host is not defined.");
				}

				ConnectionUtil.close(con, pst, null);
				return true;
			}
		}
	}

	public static boolean joinHostEvent(int eventId, int HostId) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO Event_host  (event_id, host_id) VALUES (?,?);";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setInt(1, eventId);
				pst.setInt(2, HostId);

				int rows = pst.executeUpdate();

				System.out.println("no of rows affected:" + rows);

				ConnectionUtil.close(con, pst, null);
				return (rows > 0) ? true : false;
			}

		}

	}

	public static int findHostId(String hostName) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = " SELECT * FROM host WHERE host_name = ?;";
			int userId = -1;
			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setString(1, hostName);

				ResultSet rs = pst.executeQuery();

				// Step 06: Iterate the result
				while (rs.next()) {
					userId = rs.getInt("id");

				}

				ConnectionUtil.close(con, pst, rs);

			}
			return userId;
		}

	}

	public static boolean updateEvent(int id, String newName) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "UPDATE events  SET event_name = ? WHERE id = ? ";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setString(1, newName);
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				System.out.println("no of rows affected:" + rows);
				ConnectionUtil.close(con, pst, null);
				return (rows > 0) ? true : false;
			}
		}

	}

	public static boolean updateEvent(int id, double price) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "UPDATE events  SET price = ? WHERE id = ? ";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setDouble(1, price);
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				System.out.println("no of rows affected:" + rows);
				ConnectionUtil.close(con, pst, null);
				return (rows > 0) ? true : false;
			}
		}

	}

	public static boolean deleteEvent(int id) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "DELETE FROM Event_host WHERE event_id = ?;";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setInt(1, id);

				int rows = pst.executeUpdate();
				String queryDeleteEvents = "DELETE FROM events WHERE id = ?;";
				try (PreparedStatement pst1 = con.prepareStatement(queryDeleteEvents)) {
					pst1.setInt(1, id);
					pst1.executeUpdate();
				}
				System.out.println("no of rows affected:" + rows);
				ConnectionUtil.close(con, pst, null);

				return (rows > 0) ? true : false;
			}
		}

	}

	public static boolean readEvent() throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = " SELECT * FROM events";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				ResultSet rs = pst.executeQuery(query);

				// Step 06: Iterate the result
				while (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("event_name");
					Date emailID = rs.getDate("date");

					System.out.println("UserId:" + userId + ", UserName:" + userName + ", EMAIL ID:" + emailID);
				}

				ConnectionUtil.close(con, pst, rs);

			}
		}
		return false;

	}

	public static boolean GetEventByDate() throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM events ORDER BY date ASC;";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				ResultSet rs = pst.executeQuery(query);

				// Step 06: Iterate the result
				while (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("event_name");
					Date emailID = rs.getDate("date");

					System.out.println("event Id:" + userId + ", Event name: " + userName + ", date :" + emailID);
				}

				ConnectionUtil.close(con, pst, rs);

			}
		}
		return false;

	}

	public static boolean EventRange(LocalDate start, LocalDate end) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM events WHERE date BETWEEN ? AND ?;";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, String.valueOf(start));
				pst.setString(2, String.valueOf(end));
				ResultSet rs = pst.executeQuery();

				boolean eventsFound = false;

				// Step 06: Iterate the result
				while (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("event_name");
					Date eventDate = rs.getDate("date");

					System.out.println("UserId: " + userId + ", UserName: " + userName + ", date: " + eventDate);

					eventsFound = true;
				}

				ConnectionUtil.close(con, pst, rs);

				return eventsFound;
			}
		}
	}

}
