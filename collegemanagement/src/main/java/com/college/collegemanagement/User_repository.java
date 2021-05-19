package com.college.collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_repository {

		
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
			
			
			public String getManagement(String username)
			{
				String m="";
				try {
				PreparedStatement pt = con.prepareStatement("select username from college_register where username=?");
				pt.setString(1, username);
				ResultSet rs=pt.executeQuery();  
				if(rs.next()){  
				   m="management";
				   
				}  
				else
				{
					m=getTeacher(username);
				}
				
				con.close();  
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
				return m;
			}
			
			public String getTeacher(String username)
			{
				String m = "";
				
				try
				{
					PreparedStatement pt1 = con.prepareStatement("select is_teacher,is_student from user where username=?");
					pt1.setString(1, username);
					ResultSet rs1=pt1.executeQuery();  
					if(rs1.next()){  
					   if(rs1.getInt(1)==1) m="teacher";
					   else if(rs1.getInt(2)==1)
						{
							m="student";
						}
					}  
					
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				return m;
			}
}
