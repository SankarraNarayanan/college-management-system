package com.college.collegemanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

	Connection con = null;
	public TeacherRepository()
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
	
	
	
	public void addStudent(String t_id,String username, String name, String password,int semester, String department) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert into students (s_id,t_id, name,"
					+ "password,semester,department) values(?, ?, ?, ?, ?, ?)"); 
			stmt.setString(1, username);
			stmt.setString(2, t_id);
			stmt.setString(3, name);
			stmt.setString(4, password);
			stmt.setInt(5, semester);
			stmt.setString(6,department);
			

			stmt.executeUpdate();
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public List<Student> getStudent(String username) {
		List<Student> students = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("select s_id,Name,department from students where t_id=?");  
		
		stmt.setString(1, username);
		ResultSet rs=stmt.executeQuery();  
		while(rs.next())  
		{
			Student s1 = new Student();
			s1.setS_id(rs.getString(1));
			s1.setName(rs.getString(2));
			s1.setDepartment(rs.getString(3));
			students.add(s1);
		}
		con.close();  
		}
		
		catch (Exception e) {
			
			System.out.println(e);
		}
		return students;
	}

	public void deleteStudent(String username,String t_username) {
		try {
			PreparedStatement stmt=con.prepareStatement("delete from students where s_id=? and t_id=?"); 
			
			
			stmt.setString(1,username);
			stmt.setString(2, t_username);
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public void Enroll(String course_id, String course_name, int sem, String department) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert into course_assigned (course_id,course_name,semester,"
					+ "department) values(?,?,?,?)"); 
			
			
			stmt.setString(1,course_id);
			stmt.setString(2,course_name);
			stmt.setInt(3,sem);
			stmt.setString(4,department);
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public void assignment(String username,int sem,String course,String courseid,String department, String title, String description,Date due) {
		System.out.println(due);
		int wid=0;
		try {
			PreparedStatement stmt=con.prepareStatement("insert into assignment (t_id,course_id,course_name,semester,department,"
					+ "Title,description,duedate) values(?,?,?,?,?,?,?,?)"); 
			stmt.setString(1, username);
			stmt.setString(2, courseid);
			stmt.setString(3, course);
			stmt.setInt(4, sem);
			stmt.setString(5, department);
			
			stmt.setString(6, title);
			stmt.setString(7, description);
			stmt.setDate(8, due);
			stmt.executeUpdate();
			
			PreparedStatement stmt1  = con.prepareStatement("select w_id from assignment where t_id=?");
			stmt1.setString(1, username);
			ResultSet rs = stmt1.executeQuery();
			while(rs.next())
			{
				wid=rs.getInt(1);
			}
			System.out.println(wid);
			stmt  = con.prepareStatement("INSERT INTO view_status(s_id,w_id)\n"
					+ "SELECT students.s_id,assignment.w_id\n"
					+ "from students INNER JOIN assignment ON\n"
					+ "students.t_id=assignment.t_id\n"
					+ "WHERE w_id=?");
			stmt.setInt(1, wid);
			stmt.executeUpdate();
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public List<Courses> getCourse(String username) {
		List<Courses> courses = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("SELECT  course_name from staffs_assigned where t_id=?");  
		stmt.setString(1, username);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Courses c1 = new Courses();
			
			c1.setCourse_name(rs.getString(1));
			
			courses.add(c1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return courses;
	}

	public List<Student> Nfa(int w_id) {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("select s_id, w_id from view_status where w_id=?");  
		stmt.setInt(1, w_id);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Student s1 = new Student();
		    s1.setS_id(rs.getString(1));
		    s1.setW_id(rs.getInt(2));
		    students.add(s1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return students;
		
	}

	public List<Assignment> allAssignments(String username) {
		List<Assignment> assignments = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("select w_id,t_id,course_name,semester,department,"
				+ "Title,description from assignment where t_id=?");  
		stmt.setString(1, username);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Assignment a1 = new Assignment();
			a1.setW_id(rs.getInt(1));
			a1.setT_id(rs.getString(2));
			a1.setCourse_name(rs.getString(3));
			a1.setSemester(rs.getInt(4));
			a1.setDepartment(rs.getString(5));
			a1.setTitle(rs.getString(6));
			a1.setDescription(rs.getString(7));
			assignments.add(a1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return assignments;
	}

	public void studUpdate(String username, int sem) {
		try {
			PreparedStatement stmt=con.prepareStatement("update students set semester=? where s_id=?");  
			stmt.setInt(1, sem);
			stmt.setString(2, username);
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

	public void resetPassword(String password,String username) {
		try {
			PreparedStatement stmt=con.prepareStatement("update teachers set password=? where t_id=?");  
			
			stmt.setString(1, password);
			stmt.setString(2, username);
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

}
