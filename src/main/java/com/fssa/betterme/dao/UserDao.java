package com.fssa.betterme.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


import com.fssa.betterme.exception.UserDAOException;
import com.fssa.betterme.model.Gender;
import com.fssa.betterme.model.User;
import com.fssa.betterme.util.ConnectionUtil;

public class UserDao {

	static final String ID_TAB = "id";
	static final String NAME_TAB = "username";
	static final String EMAIL_TAB = "email";
	static final String PASSWORD_TAB = "password";
	static final String MOBILE_TAB = "mobile_number";
	static final String GENDER_TAB = "gender";

	public boolean addUser(User user) throws UserDAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO users (username, mobile_number, email, gender, password) VALUES (?,?,?,?,?);";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				// Set parameters using a single try block
				pst.setString(1, user.getUsername());
				pst.setLong(2, user.getPhoneNumber());
				pst.setString(3, user.getEmail());
				pst.setString(4, user.getGender().toString());
				pst.setString(5, user.getPassword());

				// Execute the update and check the affected row count
				int rowsAffected = pst.executeUpdate();

				if (rowsAffected <= 0) {
					throw new UserDAOException("insert fail");
				}

				return true;
			}
		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}
	}

	public boolean updateUser(User user) throws UserDAOException {
		String query = "UPDATE users SET username =?,mobile_number =? WHERE id = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, user.getUsername());
			pst.setLong(2, user.getPhoneNumber());
			pst.setInt(3, user.getId());

			int rowsAffected = pst.executeUpdate();
		

			if (rowsAffected <= 0) {
				throw new UserDAOException("Update failed");
			}
			return true;

		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}

	}

	// This method checks if an user with the given name already exists in the
	// database.
	public boolean doesUserExist(String email) throws UserDAOException {
		String query = "SELECT COUNT(*) FROM users WHERE email = ?";

		try (Connection con = ConnectionUtil.getConnection(); 
			PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, email);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count == 1; // If count ==1 , user exists; otherwise, it doesn't.
				}
			}
		} catch (Exception e) {
		
			throw new UserDAOException(e.getMessage());
		}

		return false; // Default return value if an error occurs.
	}

	public boolean deleteUser(User user) throws UserDAOException {

		String query = "DELETE FROM users WHERE id = ?";
	
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setInt(1, user.getId());

				int rows = pst.executeUpdate();


				if (rows <= 0) {
					throw new UserDAOException("user delete failed");
				}

				ConnectionUtil.close(con, pst, null);

				return true;
			}
		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}

	}

	public User getUserByEmail(String name) throws UserDAOException {
		User user = null;

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, username, gender, email, password, mobile_number FROM users where email = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, name);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					user = createUser(rs);

				}
			}
		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}

		return user;

	}

	public static User getUserById(int id) throws UserDAOException {
		User user = null;

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, username, gender, email, password, mobile_number FROM users where id = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					user = createUser(rs);

				}
			}
		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}

		return user;

	}

	

	public List<User> readAllUsers() throws UserDAOException {
		List<User> users = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id, username, gender, email, password, mobile_number FROM users ";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					User user = createUser(rs);
					users.add(user);

				}
			}
		} catch (SQLException e) {
			throw new UserDAOException(e.getMessage());
		}

		return users;
	}

	private static User createUser(ResultSet rs) throws SQLException {
		int userId = rs.getInt(ID_TAB);
		String userName = rs.getString(NAME_TAB);
		long mobileNumber = rs.getLong(MOBILE_TAB);
		String email = rs.getString(EMAIL_TAB);
		Gender gender = Gender.fromString(rs.getString(GENDER_TAB));
		String password = rs.getString(PASSWORD_TAB);

		return new User(userId, userName, email, password, mobileNumber, gender);
		

	}

}
