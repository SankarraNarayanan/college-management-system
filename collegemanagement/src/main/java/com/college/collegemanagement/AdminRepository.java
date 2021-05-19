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
	
	public void createManagement(Management m1) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert into college_register (college_id, college_name,username, "
					+ "password,location) values(?, ?, ?,?,?)"); 
			stmt.setInt(1, m1.getCollege_id());
			stmt.setString(2, m1.getCollege_name());
			stmt.setString(3, m1.getUsername());
			stmt.setString(4,m1.getPassword());
			stmt.setString(5, m1.getLocation());
			stmt.executeUpdate();
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public List<String> viewmanagement() {
		
		List<String> managements = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from college_register");  
		while(rs.next())  
		{
			
			managements.add(rs.getString(2));
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return managements;
	}

}
