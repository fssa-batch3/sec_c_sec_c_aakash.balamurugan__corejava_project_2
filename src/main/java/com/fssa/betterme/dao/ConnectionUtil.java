package com.fssa.betterme.dao;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;


public abstract class ConnectionUtil {

		 
	
	 
	    	  public static Connection getConnection() {
	    	        Connection con = null;

	    	        String url, userName, passWord;

	    	        if (System.getenv("CI") != null) {
	    	            url = System.getenv("jdbc:mysql://164.52.216.41:3306/aakash_balamurugan_corejava_project");
	    	            userName = System.getenv("GR3iOU3e2faf");
	    	            passWord = System.getenv("fd8a6b14-4219-4c47-85ac-86b496585e59");
	    	        } else {
	    	            Dotenv env = Dotenv.load();
	    	            url = env.get("jdbc:mysql://164.52.216.41:3306/aakash_balamurugan_corejava_project");
	    	            userName = env.get("GR3iOU3e2faf");
	    	            passWord = env.get("fd8a6b14-4219-4c47-85ac-86b496585e59");
	    	        }

	    	        try {
	    	            Class.forName("com.mysql.cj.jdbc.Driver");
	    	            con = DriverManager.getConnection(url, userName, passWord);
	    	        } catch (Exception e) {
	    	            e.printStackTrace();
	    	            throw new RuntimeException("Unable to connect to the database");
	    	        }
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
	

       

