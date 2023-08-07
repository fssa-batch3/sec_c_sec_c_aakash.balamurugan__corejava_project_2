package com.fssa.betterme.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.*;





public class EventDao {

//	public static void main(String[] args) throws SQLException {
//		Scanner s = new Scanner(System.in);
////	System.out.println("Event name:");
////		String eventName = s.nextLine();
////		System.out.println("Event description:");
////		String eventDescription = s.nextLine();
////		
////		System.out.println("Event eventAddress:");
////		String eventAddress = s.nextLine();
////		
////		System.out.println("Event date: year month date");
////		LocalDate date = LocalDate.of(s.nextInt(), s.nextInt(), s.nextInt())  ;
////		
////		System.out.println("Event time: hour minute ");
////		LocalTime time = LocalTime.of(s.nextInt(), s.nextInt()) ;
////		
////		System.out.println("Event price: ");
////		double price = s.nextDouble();
//		
////		addEvents(eventName,eventDescription,eventAddress,date,time,price);
////		updateEvent(1, "new event name");
////		updateEvent(3, 200);
////		deleteEvent(4);
//		readEvent();
//		
//	}
		
		public static boolean addEvents(String eventName,String eventDescription,String eventAddress,LocalDate date,LocalTime time,double price)  throws SQLException {
		
		try(Connection con = ConnectionUtil.getConnection()){
			
			
			
			String query = "INSERT INTO events  (event_name,event_description,event_address,date,time,price ) VALUES (?,?,?,?,?,?);";
			
			try(PreparedStatement pst = con.prepareStatement(query);){
				
				pst.setString(1,eventName );
				pst.setString(2,eventDescription );
				pst.setString(3,eventAddress );
				pst.setDate(4,Date.valueOf(date) );
				pst.setTime(5,Time.valueOf(time) );
				pst.setDouble(6,price );
				
				int rows = pst.executeUpdate();
				
				System.out.println("no of rows affected:" + rows);
				
				ConnectionUtil.close(con, pst, null);
				
			}
		
		
	}
		return false;
	

}
		
		public static boolean updateEvent(int id , String newName)  throws SQLException {
			
			try(Connection con = ConnectionUtil.getConnection()){

				String query = "UPDATE events  SET event_name = ? WHERE id = ? ";
				
				try(PreparedStatement pst = con.prepareStatement(query);){

					pst.setString(1,newName );
					pst.setInt(2,id );
					
					int rows = pst.executeUpdate();
					
					System.out.println("no of rows affected:" + rows);
					ConnectionUtil.close(con, pst, null);
				}		
		}
			return false;
		

	}
		
		public static boolean updateEvent(int id , double price)  throws SQLException {
			
			try(Connection con = ConnectionUtil.getConnection()){

				String query = "UPDATE events  SET price = ? WHERE id = ? ";
				
				try(PreparedStatement pst = con.prepareStatement(query);){
					
					
					pst.setDouble(1,price );
					pst.setInt(2,id );
					
					int rows = pst.executeUpdate();
					
					System.out.println("no of rows affected:" + rows);
					ConnectionUtil.close(con, pst, null);
				}		
		}
			return false;
		

	}
		
	public static boolean deleteEvent(int id )  throws SQLException {
			
			try(Connection con = ConnectionUtil.getConnection()){

				String query = "DELETE FROM events WHERE id = ?;";
				
				try(PreparedStatement pst = con.prepareStatement(query);){
					
					
					
					pst.setInt(1,id );
					
					int rows = pst.executeUpdate();
					
					System.out.println("no of rows affected:" + rows);
					ConnectionUtil.close(con, pst, null);
				}		
		}
			return false;
		

	}
	
	public static boolean readEvent( )  throws SQLException {
		
		try(Connection con = ConnectionUtil.getConnection()){

			String query = "SELECT * FROM events";
			
			try(PreparedStatement pst = con.prepareStatement(query);){
				
				 ResultSet rs = pst.executeQuery(query);
		         
			        //Step 06: Iterate the result
			        while ( rs.next()) {
			        	int userId = rs.getInt("id");            
			        	String userName = rs.getString("event_name");
			            Date emailID = rs.getDate("date");
			             
			            System.out.println("UserId:" + userId +", UserName:" + userName + ", EMAIL ID:" + emailID);
			        }	
			        
			        ConnectionUtil.close(con, pst, rs);
				


			}		
	}
		return false;
	

}
	
	public static boolean GetEventByDate( )  throws SQLException {
		
		try(Connection con = ConnectionUtil.getConnection()){

			String query = "SELECT * FROM events ORDER BY date ASC;";
			
			try(PreparedStatement pst = con.prepareStatement(query);){
				
				 ResultSet rs = pst.executeQuery(query);
		         
			        //Step 06: Iterate the result
			        while ( rs.next()) {
			        	int userId = rs.getInt("id");            
			        	String userName = rs.getString("event_name");
			            Date emailID = rs.getDate("date");
			             
			            System.out.println("event Id:" + userId +", Event name: " + userName + ", date :" + emailID);
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

	            //Step 06: Iterate the result
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
