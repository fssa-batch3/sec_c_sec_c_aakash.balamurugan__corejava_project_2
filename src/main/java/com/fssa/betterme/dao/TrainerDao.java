package com.fssa.betterme.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.model.Trainer;
import com.fssa.betterme.util.ConnectionUtil;




public class TrainerDao {



	
	
	public boolean addTrainer(Trainer trainer) throws DAOException {
	    try (Connection con = ConnectionUtil.getConnection()) {
	        String sql = "{CALL InsertExpertWithEducationExperience(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	        CallableStatement callableStatement = con.prepareCall(sql);

	        // Set the input parameters from the Trainer object
	        callableStatement.setString(1, trainer.getTrainerName());
	        callableStatement.setString(2, trainer.getImageLink());
	        callableStatement.setString(3, trainer.getEmail());
	        callableStatement.setString(4, trainer.getLoginPass());
	        callableStatement.setString(5, trainer.getOccupation());
	        callableStatement.setString(6, trainer.getContent());
	     
	        callableStatement.setString(7, trainer.getStartTime());
	        callableStatement.setString(8, trainer.getEndTime());
	        

	        // Set the JSON arrays as strings for education and experience records
	        String educationJson = trainer.getEducation();
	        String experienceJson = trainer.getExprience();
	        callableStatement.setString(9, educationJson);
	        callableStatement.setString(10, experienceJson);

	        // Execute the stored procedure
	        return callableStatement.execute();

	       

	        

	      
	    } catch (SQLException e) {
	        throw new DAOException(e.getMessage());
	    }
	}



	
	public  boolean deleteTrainerById(int hostId) throws DAOException {
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
	
	public  Trainer findTrainerByEmail(String email) throws DAOException {
		Trainer trainer =null;
		
	    String query = "SELECT id, person_name, image_link, occupation , email FROM Trainers WHERE email = ?";

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
	
	public  String getTrainnerPassByEmail(String email) throws DAOException {
		String trainer =null;
		
	    String query = "SELECT login_pass  FROM Trainers WHERE email = ?";

	    try (Connection con = ConnectionUtil.getConnection(); 
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setString(1, email);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	trainer =rs.getString("login_pass");
	                
	            }
	        }
	        return trainer;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	public  Trainer findTrainerById(int id) throws DAOException {
		Trainer trainer =null;
		
	    String query = "{CALL GetTrainerDetails(?)}";

	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) { 

	        pst.setInt(1, id);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	trainer = createTrainerFullDetails(rs);
	                
	            }
	        }
	        return trainer;
	    } catch (SQLException e) {
	        throw new DAOException( e.getMessage());
	    }
	}
	
	
	public  List<Trainer> readAllTrainer() throws DAOException {
		List<Trainer> trainers = new ArrayList<>();
		
	    String query = "SELECT id, person_name, image_link, occupation , email FROM Trainers";
	   

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
	
	
	
	

	
	private static Trainer createTrainer(ResultSet rs) throws SQLException   {
		Trainer mTrainer = null;
			int mId = rs.getInt("id");
		 	String mName = rs.getString("person_name");
	        String mImage = rs.getString("image_link");
	        String mOccupation = rs.getString("occupation"); 
	        String mEmail = rs.getString("email");
	     
	        mTrainer = new Trainer(mId, mName, mImage, mOccupation, mEmail );
			return mTrainer;
		
	}
	
	private static Trainer createTrainerFullDetails(ResultSet rs) throws SQLException   {
		Trainer mTrainer = null;
		
		
			int mId = rs.getInt("trainer_id");
		 	String mName = rs.getString("trainer_name");
	        String mImage = rs.getString("image_link");
	        String mOccupation = rs.getString("occupation"); 
	        String mContent = rs.getString("content");
	        String mStartTime = rs.getString("Start_time");
	        String mEndTime = rs.getString("end_time");
	        String mEducation = rs.getString("education");
	        String mExperience = rs.getString("experience");
	        String mEmail = rs.getString("email");
	     
	        mTrainer = new Trainer(mId, mName, mImage,  mEmail ,mOccupation, mContent, mStartTime, mEndTime, mEducation, mExperience );
			return mTrainer;
		
	}

}
