package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.model.Trainner;
import com.fssa.betterme.util.ConnectionUtil;



public class HostDao {



	
	
	public static boolean addHost(Trainner host) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String query = "INSERT INTO trainers (host_name, mobile_number, email) VALUES (?, ?, ?)";
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

	
	public static boolean updateHost(Trainner host) throws DAOException {
	    String query = "UPDATE trainers SET mobile_number = ?, email = ? WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, host.getContactNumber());
	        pst.setString(2, host.getEmail());
	        pst.setInt(3, host.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new DAOException("No host with the given name was found for updating.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}


	
	public static boolean deleteHostByHostId(int hostId) throws DAOException {
	    String query = "DELETE FROM trainers WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setInt(1, hostId);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected == 0) {
	            throw new DAOException("No host with the given name was found for deleting.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public static Trainner findHostByEmail(String email) throws DAOException {
		Trainner host =null;
		
	    String query = "SELECT id, host_name, mobile_number, email FROM trainers WHERE email = ?";

	    try (Connection con = ConnectionUtil.getConnection(); 
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, email);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	host =createHost(rs);
	                
	            }
	        }
	        return host;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public static Trainner findHostById(int id) throws DAOException {
		Trainner host =null;
		
	    String query = "SELECT id, host_name, mobile_number, email FROM trainers WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setInt(1, id);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	host = createHost(rs);
	                
	            }
	        }
	        return host;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	
	public static List<Trainner> readAllHost() throws DAOException {
		List<Trainner> hosts = new ArrayList<>();
		
	    String query = "SELECT id, host_name, mobile_number, email FROM trainers";
	   

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	       

	        try (ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	            	hosts.add(createHost(rs));
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }

	    return hosts;
	}
	

	
	static Trainner createHost(ResultSet rs) throws SQLException   {
		Trainner host = null;
			int hostId = rs.getInt("id");
		 	String hostName = rs.getString("host_name");
	        String mobileNumber = rs.getString("mobile_number");
	        String email = rs.getString("email");
	     
	        host = new Trainner(hostId,hostName,mobileNumber,email );
			return host;
		
	}

}
