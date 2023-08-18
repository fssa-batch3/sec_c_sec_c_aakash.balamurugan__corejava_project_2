package com.fssa.betterme.dao;


import java.sql.Connection;

import com.fssa.betterme.util.ConnectionUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Time;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.logger.Logger;
import com.fssa.betterme.objects.Events;

public class EventDao {
	
	static String rowAffected = "no of rows affected:";
	static Logger log = new Logger();
	static String event = "event_name";
	static String priceValue = "price";

	// adding new row to the table
	public static boolean addEvent(Events event) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        // Retrieve host ID based on host name
	        int hostId = findHostId(event.getHost().getHostName(), con);

	        String query = "INSERT INTO events (event_name, event_description, event_address, date, time, price, host_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setString(1, event.getEventName());
	            pst.setString(2, event.getEventDescription());
	            pst.setString(3, event.getEventAddress());
	            pst.setDate(4, Date.valueOf(event.getEventDate()));
	            pst.setTime(5, Time.valueOf(event.getEventTime()));
	            pst.setDouble(6, event.getPrice());
	            if (hostId == -1) {
	                throw new DAOException("host not found");
	            }
	            
	            pst.setInt(7, hostId);

	            int rowsAffected = pst.executeUpdate();
	            
	            if (rowsAffected <= 0) {
	                throw new DAOException("Failed to insert event.");
	            }

	            return true;
	    
	    
	       }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		}
	}

	public static int findHostId(String hostName,Connection con) throws DAOException  {

		

			String query = " SELECT * FROM hosts WHERE host_name = ?;";
			int userId = -1;
			try (PreparedStatement pst = con.prepareStatement(query);) {

				pst.setString(1, hostName);

				ResultSet rs = pst.executeQuery();

				// Step 06: Iterate the result
				while (rs.next()) {
					userId = rs.getInt("id");
					

				}
				ConnectionUtil.close(null, pst, rs);
				
			} catch (SQLException e) {
		        throw new DAOException( e.getMessage());
			}
			return userId;
		

	}

	 public boolean updateEvent(Object oldValue, String columnName, Object newValue) throws  DAOException {
	        String query1 = "UPDATE events SET " + columnName + " = ? WHERE "+columnName +" = ?";
	        String query = query1;
	        
	        try (Connection con = ConnectionUtil.getConnection()){
	             try(PreparedStatement pst = con.prepareStatement(query)) {
	            
	            if (newValue instanceof String str) {
	                pst.setString(1, str);
	            } else if (newValue instanceof Double dou) {
	                pst.setDouble(1, dou);
	            }
	            
	            if (oldValue instanceof String str) {
	                pst.setString(2, str);
	            } else if (oldValue instanceof Double dou) {
	                pst.setDouble(2, dou);
	            }
	          
	            
	            int rows = pst.executeUpdate();
	            ConnectionUtil.close(con, pst, null);
	            return rows > 0;
	        }} catch (SQLException e) {
		        throw new DAOException( e.getMessage());
			}
	    }


	public static boolean deleteEvent(Events event) throws  DAOException {

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
		} catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		}

	}

	 public static List<Events> readEvents() throws DAOException {
	        List<Events> events = new ArrayList<>();
	        
	        try (Connection con = ConnectionUtil.getConnection()) {
	            String query = "SELECT * FROM events";
	            
	            try (PreparedStatement pst = con.prepareStatement(query);
	                 ResultSet rs = pst.executeQuery();) {
	                
	                while (rs.next()) {
	                   
	                    String eventName = rs.getString(event);
	                    Date eventDate = rs.getDate("date");
	                    Time eventTime = rs.getTime("time");
	                    double price = rs.getDouble(priceValue);
	                    
	                   
	                
	                    Events event = new Events( eventName, eventDate, eventTime, price);
	                    events.add(event);
	                    
	                    
	                }
	            }
	        } catch (SQLException e) {
	            throw new DAOException(e.getMessage());
	        }
	        
	        return events;
	    }


	public static  List<Events> getEventByDate() throws DAOException {
		 List<Events> events = new ArrayList<>();
	        
		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM events ORDER BY date ASC;";

			try (PreparedStatement pst = con.prepareStatement(query);) {

				ResultSet rs = pst.executeQuery(query);

				
			     while (rs.next()) {
	                  
	                    String eventName = rs.getString(event);
	                    Date eventDate = rs.getDate("date");
	                    Time eventTime = rs.getTime("time");
	                    double price = rs.getDouble(priceValue);
	                    
	                   
	                
	                    Events event = new Events( eventName, eventDate, eventTime, price);
	                    events.add(event);
	                    
	                    
	                }

				ConnectionUtil.close(con, pst, rs);

			}
		} catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		}
		return events;

	}

	public static  List<Events> eventRange(LocalDate start, LocalDate end) throws  DAOException {
		 List<Events> events = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM events WHERE date BETWEEN ? AND ?;";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, String.valueOf(start));
				pst.setString(2, String.valueOf(end));
				ResultSet rs = pst.executeQuery();

				

			    while (rs.next()) {
             
                    String eventName = rs.getString(event);
                    Date eventDate = rs.getDate("date");
                    Time eventTime = rs.getTime("time");
                    double price = rs.getDouble(priceValue);
                    
                   
                
                    Events event = new Events( eventName, eventDate, eventTime, price);
                    events.add(event);
                    
                    
                }

				ConnectionUtil.close(con, pst, rs);

				return events;
			}
		} catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		}
	}



	


}
