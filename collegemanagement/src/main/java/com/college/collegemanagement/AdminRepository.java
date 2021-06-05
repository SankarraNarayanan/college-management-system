package com.college.collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
	
Connection con =null;
	
	public AdminRepository()
	{
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");   
		}
		 catch(Exception e)
		 { 
			 System.out.println(e);
		 } 
		
	}
	
	public String createManagement(int cid, String college_name,
			String username, String password,
			String location
			) {
		String res = "";
		try {
			PreparedStatement stmt=con.prepareStatement("insert into college_register (college_id, college_name,username, "
					+ "password,location) values(?, ?, ?,?,?)"); 
			stmt.setInt(1,cid);
			stmt.setString(2, college_name);
			stmt.setString(3, username);
			stmt.setString(4,password);
			stmt.setString(5, location);
			stmt.executeUpdate();
			res="success";
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
		return res;
	}

	public List<Management> viewmanagement() {
		
		List<Management> managements = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select college_id,college_name,location from college_register");  
		while(rs.next())  
		{
			
			Management m1 = new Management();
			m1.setCollege_id(rs.getInt(1));
			m1.setCollege_name(rs.getString(2));
			m1.setLocation(rs.getString(3));
			
			managements.add(m1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return managements;
	}

	public void deletemanagement(int cid) {
		
		try {
			PreparedStatement stmt=con.prepareStatement("delete from college_register where college_id=?");  
			stmt.setInt(1, cid);
			stmt.executeUpdate();  
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public void editmanagement(int cid, String collegename, String location) {
		System.out.println(location+"final");
		try {
			PreparedStatement stmt=con.prepareStatement("update college_register set college_name=?,location=? where college_id=?");  
			
			stmt.setString(1,collegename );
			stmt.setString(2, location);
			stmt.setInt(3, cid);
			stmt.executeUpdate();  
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	

}
