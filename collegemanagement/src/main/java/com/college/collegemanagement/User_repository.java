package com.college.collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User_repository {

	List<String> value= new ArrayList<>();
		Connection con =null;
			
	public User_repository()
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
	public String getAdmin(String username, String password)
	{
		String u="";
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT username,password FROM admin where username=? and password=?");
		    ps.setString(1, username);
		    ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
		    if(rs.next())
		    {
		    	if(rs.getString(1)!=null && rs.getString(2)!=null)
		    		{ u="admin"; }
		    	
	    	}
		    else
	    	{
	    		u=getStudent(username,password);
	    	}
		    
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return u;
		
	}
	public String getStudent(String username, String password)
	{
		String u="";
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT s_id,password FROM students WHERE s_id=? and password=? ");
		    ps.setString(1, username);
		    ps.setString(2, password);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next())
		    {
		    	if(rs.getString(1)!=null && rs.getString(2)!=null)
		    		{ u="student"; }
		    	
	    	}
		    else
	    	{
	    		u=getTeacher(username,password);
	    	}
		    
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return u;
	}


	public String getTeacher(String username, String password) 
	{
		String u1 = "";
		try
		{
			PreparedStatement ps1 = con.prepareStatement("SELECT t_id,password FROM teachers WHERE t_id=? and password=? ");
		    ps1.setString(1, username);
		    ps1.setString(2, password);
		    ResultSet rs1 = ps1.executeQuery();
		    if(rs1.next())
		    {
		    	if(rs1.getString(1)!=null && rs1.getString(2)!=null)
		    		{u1="teacher";}
		    	
		    	
		    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return u1;
		
	}	
	public List<String> teacherdetails(String username) 
	{
		List<String> details=new ArrayList<>();
		try
		{
			PreparedStatement ps1 = con.prepareStatement("SELECT semester,department FROM staffs_assigned WHERE t_id=? ");
		    ps1.setString(1, username);
		    
		    ResultSet rs1 = ps1.executeQuery();
		    while(rs1.next())
		    {
		    	String sem = String.valueOf(rs1.getInt(1));
		    	details.add(sem);
		    	details.add(rs1.getString(2));
		    	
		    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return details;
		
	}		
}
