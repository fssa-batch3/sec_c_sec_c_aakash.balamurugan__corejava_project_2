package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.betterme.exception.BookDAOException;
import com.fssa.betterme.util.ConnectionUtil;

public class RecordedVideoDao {
	public static boolean addVideo(int trainner, int userId, String video) throws BookDAOException {
		String query = "INSERT INTO recorded_video ( trainner_id, user_id, video) VALUES (? ,?, ?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, trainner);
			pst.setInt(2, userId);
			pst.setString(3, video);

			int rowsInserted = pst.executeUpdate();

			if (rowsInserted == 0) {
				throw new BookDAOException("Add failed");
			} else {
				return rowsInserted == 1;
			}

		} catch (SQLException e) {
			throw new BookDAOException(e.getMessage());
		}

	}
}
