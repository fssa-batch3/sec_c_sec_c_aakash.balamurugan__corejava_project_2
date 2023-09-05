
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

	  private static final String DATABASE_HOST = System.getenv("DATABASE_HOST");
      private static final String DATABASE_USERNAME = System.getenv("DATABASE_USERNAME");
	  private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");

	/**
	 * Get a connection to the database.
	 * 
	 * @return The database connection.
	 * @throws DAOException
	 * @throws RuntimeException if unable to connect to the database.
	 */
	public static Connection getConnection()  {
		Connection con = null;

		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(DATABASE_HOST, DATABASE_USERNAME, DATABASE_PASSWORD);
	        } catch (Exception e) {
	        	Logger.info(e.getMessage());
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
