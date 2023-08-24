package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.model.EventHost;
import com.fssa.betterme.util.ConnectionUtil;
import com.fssa.betterme.util.Logger;


public class HostDao {

	static Logger log = new Logger();

	
	public static boolean addHost(EventHost host) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String query = "INSERT INTO hosts (host_name, mobile_number, email) VALUES (?, ?, ?)";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            // Set parameters using a single try block
	            pst.setString(1, host.getHostName());
	            pst.setString(2, host.getContactNumber());
	            pst.setString(3, host.getEmail());

	            // Execute the update and check the affected row count
	            int rowsAffected = pst.executeUpdate();
	          
	    		if (rowsAffected <= 0) {
					throw new DAOException("Failed to insert host.");
				}

				return true;
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
		} 
	}

	
	public boolean updateHost(EventHost host) throws DAOException {
	    String query = "UPDATE hosts SET mobile_number = ?, email = ? WHERE host_name = ?";

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
	    String query = "DELETE FROM hosts WHERE host_name = ?";

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
	
	public static List<EventHost> readAllHost() throws DAOException {
		List<EventHost> hosts = new ArrayList<>();
		
	    String query = "SELECT * FROM hosts";
	   

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	       

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	int  hostId = rs.getInt("id");
	            	String hostName = rs.getString("host_name");
	                String mobileNumber = rs.getString("mobile_number");
	                String email = rs.getString("email");
	                
	                EventHost host = new EventHost(hostId,hostName,mobileNumber,email );
	                hosts.add(host);
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }

	    return hosts;
	}

}
