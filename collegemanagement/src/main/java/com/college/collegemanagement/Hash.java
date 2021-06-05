package com.college.collegemanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {
	
	 public String encrypt(String password)
    {
    	try {
			MessageDigest hash = MessageDigest.getInstance("MD5");
			
			hash.update(password.getBytes());
			byte[] encrypted = hash.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : encrypted)
			{
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
			}
    	 catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
    	return "";
    }
}
