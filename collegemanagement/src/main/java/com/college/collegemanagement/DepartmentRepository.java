package com.college.collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DepartmentRepository {
	
	Connection con =null;
	
	public DepartmentRepository()
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
	
	public void createTeacher( String tid, String name,  String password) {
		
		
		try {
			PreparedStatement stmt=con.prepareStatement("insert into teachers (t_id, name, password) values(?, ?, ?)"); 
			
			stmt.setString(1, tid);
			stmt.setString(2, name);
			stmt.setString(3,password);
			
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
	
	
	
	public void createCourse(String courseid, String coursename, int semester, String department) {
		System.out.println(department);
		try {
			PreparedStatement stmt=con.prepareStatement("insert into courses (course_id, course_name,semester,department) values(?, ?,?, ?)"); 
			
			stmt.setString(1, courseid);
			stmt.setString(2, coursename);
			stmt.setInt(3,semester);
			stmt.setString(4, department);
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
		
	}
	public List<Courses> getCourse() {
		List<Courses> courses = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT course_id, course_name, semester,department \n"
				+ "FROM courses\n"
				+ "where course_id NOT IN (SELECT DISTINCT course_id FROM staffs_assigned); ");  
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Courses c1 = new Courses();
			c1.setCourse_id(rs.getString(1));
			c1.setCourse_name(rs.getString(2));
			c1.setSemester(rs.getInt(3));
			c1.setDepartment(rs.getString(4));
			courses.add(c1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return courses;
	}
	public void addDept(String department) {

		try {
			PreparedStatement stmt=con.prepareStatement("insert into department (department_name) values(?)"); 
			
			
			stmt.setString(1,department);
			
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	public List<Department> getDept() {
		List<Department> dept = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from department");  
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Department d1 = new Department();
			d1.setDepartment(rs.getString(1));
			dept.add(d1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return dept;
	}
	public void delDept(String department) {
		try {
			PreparedStatement stmt=con.prepareStatement("delete from department where department_name=?"); 
			
			
			stmt.setString(1,department);
			
			
			stmt.executeUpdate();
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	
	public void assignTeacher(String tid, String department, int sem, String course, String courseid,String section) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert into staffs_assigned (t_id,course_id,course_name,semester,department,section) values(?,?,?,?,?,?)"); 
			stmt.setString(1,tid);
			stmt.setString(2, courseid);
			stmt.setString(3, course);
			stmt.setInt(4, sem);
			stmt.setString(5, department);
			stmt.setString(6, section);
			stmt.executeUpdate();
			
			
			con.close();  
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	public List<Teacher> get_assigned_teacher() {
		List<Teacher> assigned_teachers = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT teachers.t_id, teachers.name, staffs_assigned.course_name,staffs_assigned.semester,staffs_assigned.department\n"
				+ "FROM teachers\n"
				+ "INNER JOIN staffs_assigned ON teachers.t_id = staffs_assigned.t_id;");  
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Teacher t1 = new Teacher();
			t1.setTid(rs.getString(1));
			t1.setName(rs.getString(2));
			t1.setCourse_name(rs.getString(3));
			t1.setSemester(rs.getInt(4));
			t1.setDepartment(rs.getString(5));
			
			assigned_teachers.add(t1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return assigned_teachers;
	}

	public List<Teacher> get_all_teacher() {
		List<Teacher> assigned_teachers = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT t_id,name from teachers WHERE t_id NOT IN (SELECT DISTINCT t_id FROM staffs_assigned)");  
		while(rs.next())  
		{
			//System.out.println(rs.getString(3));
			Teacher t1 = new Teacher();
			t1.setTid(rs.getString(1));
			t1.setName(rs.getString(2));
			
			assigned_teachers.add(t1);
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return assigned_teachers;
	}
	
}
