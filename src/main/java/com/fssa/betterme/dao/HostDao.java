package com.fssa.betterme.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.logger.Logger;
import com.fssa.betterme.objects.*;
import com.fssa.betterme.util.ConnectionUtil;


public class HostDao {

	static String rowAffected = "no of rows affected:";
	static Logger log = new Logger();
	static String host = "host_name";
	
	public static boolean addHost(EventHost host) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String query = "INSERT INTO host (host_name, mobile_number, email) VALUES (?, ?, ?);";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            // Set parameters using a single try block
	            pst.setString(1, host.getHostName());
	            pst.setString(2, host.getContactNumber());
	            pst.setString(3, host.getEmail());

	            // Execute the update and check the affected row count
	            int rowsAffected = pst.executeUpdate();
	          
	                return rowsAffected > 0; // Successfully added the host
	          
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		} 
	}

	
	public boolean updateHost(EventHost host) throws DAOException {
	    String query = "UPDATE host SET mobile_number = ?, email = ? WHERE host_name = ?;";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, host.getContactNumber());
	        pst.setString(2, host.getEmail());
	        pst.setString(3, host.getHostName());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new DAOException("No host with the given name was found for updating.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}


	
	public static boolean deleteHostByHostName(String hostName) throws DAOException {
	    String query = "DELETE FROM host WHERE host_name = ?;";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, hostName);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected == 0) {
	            throw new DAOException("No host with the given name was found for updating.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public static boolean readAllHost() throws DAOException {
	    String query = "SELECT * FROM host;";
	   

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	       

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	String hostName = rs.getString(host);
	                String mobileNumber = rs.getString("mobile_number");
	                String email = rs.getString("email");
	               log.info("host name :"+hostName+"contact number :"+mobileNumber+"email ID :"+email );
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }

	    return true;
	}

}
