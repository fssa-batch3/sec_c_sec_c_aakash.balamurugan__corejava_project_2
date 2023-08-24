package com.fssa.betterme.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.betterme.exception.DAOException;


/**
 * Utility class for managing database connections.
 */
public abstract class ConnectionUtil {

    private ConnectionUtil() {
        // Private constructor to prevent instantiation.
    }

    static Logger log = new Logger();

    /**
     * Establishes a connection to the database.
     *
     * @return The established database connection.
     * @throws DAOException If there's an issue with establishing the connection.
     */
    public static Connection getConnection() throws DAOException {
        Connection con = null;

        String url;
        String userName;
        String passWord;

            url = System.getenv("DATABASE_HOST");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
    

        try {
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            throw new DAOException("Unable to connect to the database");
        }
 
        return con;
    }

    /**
     * Closes the database resources: connection, statement, and result set.
     *
     * @param conn The connection to be closed.
     * @param stmt The statement to be closed.
     * @param rs   The result set to be closed.
     */
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
            // Ignore the exception while closing.
        }
    }
}
