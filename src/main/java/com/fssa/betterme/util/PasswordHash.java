package com.fssa.betterme.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fssa.betterme.exception.UserValidationException;

public class PasswordHash {

	public static String hashPass(String password) throws UserValidationException {
		 try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

				StringBuilder hashsb = new StringBuilder();
				for (byte b : hashBytes) {
					hashsb.append(String.format("%02x", b));
				}

				return hashsb.toString();
			} catch (NoSuchAlgorithmException e) {

				throw new UserValidationException(e.getMessage());

			}
	        
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println(hashPass("Luffy@123"));
		} catch (UserValidationException e) {
			
			e.printStackTrace();
		}
	}
	

}
