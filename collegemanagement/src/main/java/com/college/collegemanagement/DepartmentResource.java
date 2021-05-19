package com.college.collegemanagement;



import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("management")
public class DepartmentResource {

	DepartmentRepository repo = new DepartmentRepository();
	@GET
	@Path("viewdepartment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> viewdepartment()
	{
		List<String> department = repo.getDepartment();
		 if(department.size()==0)
		 {
			 department.add("No records found");
		 }
		 return department;
	}	
	
	@POST
	@Path("addteacher/{college_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createteacher(@PathParam("college_id") int college_id,  Teacher t1)
	{
		
		String result = repo.createTeacher(college_id, t1);
	    return result;
		
	}
	
	@GET
	@Path("teacher/{college_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> viewteacher(@PathParam("college_id") int college_id)
	{
		
		List<String> teachers = repo.viewteacher(college_id);
		return teachers;
	}
	
	@POST
	@Path("addpayroll/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addParoll(@PathParam("username") String username,Payroll p1)
	{
		
		String result = repo.addpayroll(username , p1);
	    return result;
		
	}
}
