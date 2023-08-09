package com.fssa.betterme.dao;

import java.sql.*;
import com.fssa.betterme.server.*;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class ConnectionUtil {

	private ConnectionUtil() {

	}

	static Logger log = new Logger();

	public static Connection getConnection() throws DAOException {
		Connection con = null;

		String url;
		String userName;
		String passWord;

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

			throw new DAOException("Unable to connect to the database");
		}
		log.info("connected");
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
				log.info("connection removed");
			}
		} catch (SQLException e) {

			// No need re throw the exception.
		}
	}
}
