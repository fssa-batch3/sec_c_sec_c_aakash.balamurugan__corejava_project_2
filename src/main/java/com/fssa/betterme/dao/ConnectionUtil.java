package com.fssa.betterme.dao;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public  class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;

		String url, userName, passWord;

		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}

		try {

			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
		
			throw new RuntimeException("Unable to connect to the database");
		}
		return con;
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
				System.out.println("connection closed");
			}
		} catch (SQLException e) {
			
			// No need re throw the exception.
		}
	}
}
