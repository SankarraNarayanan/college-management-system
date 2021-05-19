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
	public List<String> getDepartment() {
		List<String> departments = new ArrayList<>();
		try {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select department from user");  
		while(rs.next())  
		{
			Department d1 = new Department();
			if(rs.getString(1)!=null)
			{
			if(!departments.contains(rs.getString(1)))
				departments.add(rs.getString(1));
			}
		}
		con.close();  
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return departments;
	}
	public String createTeacher( int college_id, Teacher t1) {
		System.out.println(college_id);
		String s = "";
		try {
			PreparedStatement stmt=con.prepareStatement("insert into user (college_id, name, username, "
					+ "password, is_teacher, department) values(?, ?, ?, ?, ?, ?)"); 
			stmt.setInt(1, college_id);
			stmt.setString(2, t1.getName());
			stmt.setString(3, t1.getUsername());
			stmt.setString(4,t1.getPassword());
			stmt.setInt(5, t1.getIs_teacher());
			stmt.setString(6, t1.getDepartment());
			stmt.executeUpdate();
			s="inserted into db successfully";
			con.close();  
			}
			catch (Exception e) {
				s="college not found";
				System.out.println(e);
			}
		return s;
	}
	
	public List<String> viewteacher(int college_id) {
		System.out.print(college_id);
		List<String> teachers = new ArrayList<>();
		try {
		PreparedStatement stmt=con.prepareStatement("select name from user where(college_id=? AND is_teacher=1)");  
		stmt.setInt(1, college_id);
		ResultSet rs=stmt.executeQuery();  
		while(rs.next())  
		{
			if(!teachers.contains(rs.getString(1)))
				teachers.add(rs.getString(1));
		}
		con.close();  
		}
		
		catch (Exception e) {
			
			System.out.println(e);
		}
		if(teachers.size()==0)
		{
			teachers.add("no teachers under the college found");
		}
		return teachers;
	}
	public String addpayroll(String username, Payroll p1) 
	{
		String res = "";
		try {
			PreparedStatement stmt1=con.prepareStatement("select is_teacher from user where username=?");  
			stmt1.setString(1,username);
			ResultSet rs1=stmt1.executeQuery();  
			while(rs1.next())
			{
				if(rs1.getInt(1)==1)
				{
				  res = addPayroll(username,  p1);
				}
			}
			con.close();
		}
		catch (Exception e) {
			
			System.out.println(e);
		}
		return res;
	}
	public String addPayroll(String username , Payroll p1)
	{
		String s="";
		try
		{
			PreparedStatement stmt=con.prepareStatement("insert into payroll ( username, "
					+ "allowance, deduction, net_salary) values(?, ?, ?, ?)"); 
			stmt.setString(1, username);
			stmt.setInt(2, p1.getAllowance());
			stmt.setInt(3, p1.getDeduction());
			stmt.setInt(4, (p1.getNetsalary() + p1.getAllowance())- p1.getDeduction());
			stmt.executeUpdate();
			s="payroll added successfully";
			con.close();  
		}
		catch (Exception e) {
			s="duplicates found";
			System.out.println(e);
		}
		return s;
	}
}
