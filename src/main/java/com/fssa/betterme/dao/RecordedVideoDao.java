package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.util.ConnectionUtil;

public class RecordedVideoDao {
	public static boolean addVideo(int trainner, int userId, String video) throws DAOException {
		String query = "INSERT INTO recorded_video ( trainner_id, user_id, video) VALUES (? ,?, ?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, trainner);
			pst.setInt(2, userId);
			pst.setString(3, video);

			int rowsInserted = pst.executeUpdate();

			if (rowsInserted == 0) {
				throw new DAOException("Add failed");
			} else {
				return rowsInserted == 1;
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}
	
	public static List<String> getVideoByUser(int userId) throws DAOException {
		String query = "SELECT video FROM betterme.recorded_video WHERE user_id = ?";
		List<String> urls = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()){
				urls.add(rs.getString("video"));
			}
			}catch(SQLException e) {
				throw new DAOException(e.getMessage());
			}
		
		
		return urls;
	}
	
	
}
