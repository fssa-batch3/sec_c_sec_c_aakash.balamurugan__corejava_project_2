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



public class TrainerDao {



	
	
	public static boolean addTrainer(Trainner host) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String query = "INSERT INTO Trainers (trainer_name, mobile_number, email) VALUES (?, ?, ?)";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            // Set parameters using a single try block
	            pst.setString(1, host.getTrainerName());
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

	
	public static boolean updateTrainer(Trainner host) throws DAOException {
	    String query = "UPDATE Trainers SET trainer_name = ?,mobile_number = ?, email = ? WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {
	    	pst.setString(1, host.getTrainerName());
	        pst.setString(2, host.getContactNumber());
	        pst.setString(3, host.getEmail());
	        pst.setInt(4, host.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new DAOException("No host with the given name was found for updating.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}


	
	public static boolean deleteTrainerByHostId(int hostId) throws DAOException {
	    String query = "DELETE FROM Trainers WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setInt(1, hostId);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected == 0) {
	            throw new DAOException("No Trainer with the given name was found for deleting.");
	        }
	        return true;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public static Trainner findTrainerByEmail(String email) throws DAOException {
		Trainner trainer =null;
		
	    String query = "SELECT id, trainer_name, mobile_number, email FROM trainers WHERE email = ?";

	    try (Connection con = ConnectionUtil.getConnection(); 
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, email);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	trainer =createTrainer(rs);
	                
	            }
	        }
	        return trainer;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public static Trainner findTrainerById(int id) throws DAOException {
		Trainner trainer =null;
		
	    String query = "SELECT id, trainer_name, mobile_number, email FROM Trainers WHERE id = ?";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) { 

	        pst.setInt(1, id);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	trainer = createTrainer(rs);
	                
	            }
	        }
	        return trainer;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	
	public static List<Trainner> readAllTrainer() throws DAOException {
		List<Trainner> trainers = new ArrayList<>();
		
	    String query = "SELECT id, trainer_name, mobile_number, email FROM Trainers";
	   

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	       

	        try (ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	            	trainers.add(createTrainer(rs));
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }

	    return trainers;
	}
	

	
	static Trainner createTrainer(ResultSet rs) throws SQLException   {
		Trainner host = null;
			int hostId = rs.getInt("id");
		 	String hostName = rs.getString("trainer_name");
	        String mobileNumber = rs.getString("mobile_number");
	        String email = rs.getString("email");
	     
	        host = new Trainner(hostId,hostName,mobileNumber,email );
			return host;
		
	}

}
