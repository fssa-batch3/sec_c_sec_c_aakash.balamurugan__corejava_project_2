
package com.fssa.betterme.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.betterme.exception.DAOException;

public class ConnectionUtil {
	private ConnectionUtil() {
//	private constructor
	}

	/**
	 * Get a connection to the database.
	 * 
	 * @return The database connection.
	 * @throws DAOException
	 * @throws RuntimeException if unable to connect to the database.
	 */
	public static Connection getConnection() throws DAOException {
		Connection con = null;

		String url;
		String userName;
		String passWord;

//		url = System.getenv("DATABASE_HOST");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");

		url = "jdbc:mysql://localhost:3306/betterme";
		userName = "root";
		passWord = "123456";

		try {
		Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connection");
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return con;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		Logger logger = new Logger();
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) { 
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}



}
