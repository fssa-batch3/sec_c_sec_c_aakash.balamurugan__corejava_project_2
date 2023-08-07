package com.fssa.betterme.dao;

import java.sql.*;


public abstract class ConnectionUtil {

		 
	    public static Connection getConnection()  {
	 
	        Connection con = null;
//	        String url = "jdbc:mysql:// aws.connect.psdb.cloud";
//	        String userName = "3qynvhn4mczixvw69aq3";
//	        String passWord = "pscale_pw_w3jrAE4nrSA2IoreDXZri99cr1pxZs7gIn86JHIOufP";
	        
	        
	        String url = "jdbc:mysql://localhost:3306/ betterme ";
	        String userName = "root";
	        String passWord = "123456";
	        try {
	            con = DriverManager.getConnection(url, userName, passWord);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Unable to connect to the database");
	        }
	        System.out.println("Connnection Created");
	        return con;
	    }
	     
	    public static void close(Connection conn , Statement stmt, ResultSet rs){
	         
	        try
	        {
	            if ( rs != null ){
	                rs.close();
	                
	            }
	            if ( stmt != null ) {
	                stmt.close();
	            }
	            if ( conn != null ){
	                conn.close();
	                System.out.println("connection closed");
	            }
	        }
	        catch(SQLException e){
	             e.printStackTrace();
							 // No need re throw the exception.
	        }
	    }
	}
	

       

