package com.fssa.betterme.dao;

import java.sql.Connection;

import com.fssa.betterme.server.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;


import com.fssa.betterme.objects.Events;

public class EventDao {
	
	static String rowAffected = "no of rows affected:";
	static Logger log = new Logger();

	// adding new row to the table
	public static boolean addEvent(Events event) throws SQLException, DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price) VALUES (?, ?, ?, ?, ?, ?);";
	        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	            pst.setString(1, event.getEventName());
	            pst.setString(2, event.getEventDescription());
	            pst.setString(3, event.getEventAddress());
	            pst.setDate(4, Date.valueOf(event.getEventDate()));
	            pst.setTime(5, Time.valueOf(event.getEventTime()));
	            pst.setDouble(6, event.getPrice());

	            int rows = pst.executeUpdate();
	        	log.debug(rowAffected + rows);
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
	            int hostId = findHostId(event.getHostName(), con);

	            // Associate the host with the event in the join table
	            if (hostId != -1) {
	                joinHostEvent(eventId, hostId, con);
	            } else {
	                throw new DAOException("Host is not defined.");
	            }

	            ConnectionUtil.close(con, pst, null);
	            return true;
	        }
	    }
	}


	public static boolean joinHostEvent(int eventId, int hostId,Connection con) throws SQLException {

	

			String query = "INSERT INTO Event_host  (event_id, host_id) VALUES (?,?);";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setInt(1, eventId);
				pst.setInt(2, hostId);

				int rows = pst.executeUpdate();

				log.info(rowAffected + rows);

				
				return true ;
			}

		

	}

	public static int findHostId(String hostName,Connection con) throws SQLException {

		

			String query = " SELECT * FROM host WHERE host_name = ?;";
			int userId = -1;
			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setString(1, hostName);

				ResultSet rs = pst.executeQuery();

				// Step 06: Iterate the result
				while (rs.next()) {
					userId = rs.getInt("id");

				}

				
			}
			return userId;
		

	}

	public static boolean updateEvent(int id, String newName) throws SQLException, DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "UPDATE events  SET event_name = ? WHERE id = ? ";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setString(1, newName);
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				log.info(rowAffected + rows);
				ConnectionUtil.close(con, pst, null);
				return  true ;
			}
		}

	}

	public static boolean updateEvent(int id, double price) throws SQLException, DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "UPDATE events  SET price = ? WHERE id = ? ";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setDouble(1, price);
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				log.info(rowAffected + rows);
				ConnectionUtil.close(con, pst, null);
				return  true ;
			}
		}

	}

	public static boolean deleteEvent(int id) throws SQLException, DAOException {

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
				log.info(rowAffected + rows);
				ConnectionUtil.close(con, pst, null);

				return  true ;
			}
		}

	}

	public static boolean readEvent() throws SQLException, DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = " SELECT * FROM events";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				ResultSet rs = pst.executeQuery(query);

				// Step 06: Iterate the result
				while (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("event_name");
					Date emailID = rs.getDate("date");

				log.info("UserId:" + userId + ", UserName:" + userName + ", EMAIL ID:" + emailID);
				}

				ConnectionUtil.close(con, pst, rs);

			}
		}
		return false;

	}

	public static boolean getEventByDate() throws SQLException, DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM events ORDER BY date ASC;";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				ResultSet rs = pst.executeQuery(query);

				// Step 06: Iterate the result
				while (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("event_name");
					Date emailID = rs.getDate("date");

					log.info("event Id:" + userId + ", Event name: " + userName + ", date :" + emailID);
				}

				ConnectionUtil.close(con, pst, rs);

			}
		}
		return false;

	}

	public static boolean eventRange(LocalDate start, LocalDate end) throws SQLException, DAOException {
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

					log.info("UserId: " + userId + ", UserName: " + userName + ", date: " + eventDate);

					eventsFound = true;
				}

				ConnectionUtil.close(con, pst, rs);

				return eventsFound;
			}
		}
	}

}
