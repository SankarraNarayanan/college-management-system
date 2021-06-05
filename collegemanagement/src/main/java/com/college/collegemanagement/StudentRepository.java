package com.college.collegemanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	Connection con = null;
	public StudentRepository()
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
	public void resetPassword(String password,String username) {
		try {
			PreparedStatement stmt=con.prepareStatement("update students set password=? where s_id=?");  
			
			stmt.setString(1, password);
			stmt.setString(2, username);
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	
	public List<Courses> allCourses(String username) {
		List<Courses> courses = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("SELECT staffs_assigned.t_id, staffs_assigned.course_id,staffs_assigned.course_name\n"
				+ "from staffs_assigned INNER JOIN students ON\n"
				+ "staffs_assigned.semester=students.semester AND\n"
				+ "staffs_assigned.department=students.department\n"
				+ "where students.s_id=?");  
		stmt.setString(1, username);
		
		ResultSet rs=stmt.executeQuery();
		while(rs.next())  
		{
			Courses c1 = new Courses();
			c1.setT_id(rs.getString(1));
			c1.setCourse_id(rs.getString(2));
			c1.setCourse_name(rs.getString(3));
			courses.add(c1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return courses;
	}

	public List<Assignment> allAssignments(String username,String courseid) {
		List<Assignment> assignment= new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("SELECT assignment.Title,assignment.description,assignment.duedate\n"
				+ "FROM assignment\n"
				+ "WHERE assignment.course_id=?");  
		
		stmt.setString(1, courseid);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())  
		{
			Assignment a1 = new Assignment();
			a1.setTitle(rs.getString(1));
			a1.setDescription(rs.getString(2));
			a1.setDate(rs.getDate(3));
			assignment.add(a1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return assignment;
	}
}
