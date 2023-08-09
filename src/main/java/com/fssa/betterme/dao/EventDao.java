package com.fssa.betterme.dao;

import java.sql.Connection;


import com.fssa.betterme.server.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Time;
import java.time.LocalDate;


import com.fssa.betterme.objects.Events;

public class EventDao {
	
	static String rowAffected = "no of rows affected:";
	static Logger log = new Logger();
	static String event = "event_name";

	// adding new row to the table
	public static boolean addEvent(Events event) throws SQLException, DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	    	
	    	int hostId = findHostId(event.getHost().getHostName(), con);
	    	
	        String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price,host_id) VALUES (?, ?, ?, ?, ?, ?,?);";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setString(1, event.getEventName());
	            pst.setString(2, event.getEventDescription());
	            pst.setString(3, event.getEventAddress());
	            pst.setDate(4, Date.valueOf(event.getEventDate()));
	            pst.setTime(5, Time.valueOf(event.getEventTime()));
	            pst.setDouble(6, event.getPrice());
	            pst.setInt(7, hostId);

	            int rows = pst.executeUpdate();
	        	log.info(rowAffected + rows);
	            if (rows <= 0) {
	                throw new DAOException("Failed to insert event.");
	            }


	            ConnectionUtil.close(con, pst, null);
	            return true;
	        }
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
				ConnectionUtil.close(null, pst, rs);
				
			}
			return userId;
		

	}

	 public boolean updateEvent(Object oldValue, String columnName, Object newValue) throws SQLException, DAOException {
	        String query = "UPDATE events SET " + columnName + " = ? WHERE "+columnName +" = ?";
	        
	        try (Connection con = ConnectionUtil.getConnection()){
	             try(PreparedStatement pst = con.prepareStatement(query)) {
	            
	            if (newValue instanceof String) {
	                pst.setString(1, (String) newValue);
	            } else if (newValue instanceof Double) {
	                pst.setDouble(1, (Double) newValue);
	            }
	            
	            if (oldValue instanceof String) {
	                pst.setString(2, (String) oldValue);
	            } else if (oldValue instanceof Double) {
	                pst.setDouble(2, (Double) oldValue);
	            }
	          
	            
	            int rows = pst.executeUpdate();
	            ConnectionUtil.close(con, pst, null);
	            return rows > 0;
	        }
	             }
	    }


	public static boolean deleteEvent(Events event) throws SQLException, DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

		

				
				String queryDeleteEvents = "DELETE FROM events WHERE event_name = ?;";
				try (PreparedStatement pst = con.prepareStatement(queryDeleteEvents)) {
					pst.setString(1, event.getEventName());
					pst.executeUpdate();
				
				int rows = pst.executeUpdate();
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
					int eventId = rs.getInt("id");
					String eventName = rs.getString(event);
					Date emailID = rs.getDate("date");

				log.info("EventId:" + eventId + ", EventName:" + eventName + ", EMAIL ID:" + emailID);
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
					String userName = rs.getString(event);
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
					String userName = rs.getString(event);
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
