
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

//	  private static final String DATABASE_HOST = System.getenv("DATABASE_HOST");
//      private static final String DATABASE_USERNAME = System.getenv("DATABASE_USERNAME");
//	  private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");

	/**
	 * Get a connection to the database.
	 * 
	 * @throws Exception 
	 * @throws DAOException
	 * @throws RuntimeException if unable to connect to the database.
	 */
	public static Connection getConnection()  {

		String url;
		String userName;
		String passWord;
		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			Logger.info(e.getMessage());
			
	
		}
		return null;
	

	}


	public static void close(Connection conn, Statement stmt, ResultSet rs) {

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
			Logger.info(e.getMessage());
		}
	}

}
