package com.college.collegemanagement;



import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("management")
public class DepartmentResource {

	DepartmentRepository repo = new DepartmentRepository();
	Hash hash = new Hash();
	@GET
	@Path("getcourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> getcourse()
	{
		List<Courses> courses = repo.getCourse();
		 return courses;
	}	
	
	@POST
	@Path("adddepartment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response adddept(@FormParam("department") String department) throws URISyntaxException
	{
		
		repo.addDept(department);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	@GET
	@Path("getdept")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getdept()
	{
		List<Department> dept = repo.getDept();
		 return dept;
	}	
	
	
	@POST
	@Path("deletedepartment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletedept(@FormParam("department") String department) throws URISyntaxException
	{
		System.out.println(department);
		repo.delDept(department);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@POST
	@Path("addteacher")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createteacher(@FormParam("tid") String tid,@FormParam("name") String name,
	@FormParam("password") String password
			) throws URISyntaxException
	{
		password = hash.encrypt(password);
		repo.createTeacher(tid,name,password);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@POST
	@Path("assignteacher")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response assignteacher(@FormParam("tid") String tid,@FormParam("department") String department,
			@FormParam("semester") String semester,
			@FormParam("course") String course,@FormParam("courseid") String courseid,
			@FormParam("section") String section
			) throws URISyntaxException
	{
	     int sem = Integer.parseInt(semester);	
		repo.assignTeacher(tid,department,sem,course,courseid,section);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@GET
	@Path("g_a_teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Teacher> getassigned_teacher()
	{
		List<Teacher> assigned_teachers = repo.get_assigned_teacher();
		 return assigned_teachers;
	}	
	
	@GET
	@Path("a_teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Teacher> all_teacher()
	{
		List<Teacher> assigned_teachers = repo.get_all_teacher();
		 return assigned_teachers;
	}	
	
	@POST
	@Path("addcourse")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addcourse(@FormParam("courseid") String courseid,@FormParam("coursename") String coursename,
	@FormParam("semester") String semester, @FormParam("select-department") String department
			) throws URISyntaxException
	{
	     int sem = Integer.parseInt(semester);	
		repo.createCourse(courseid,coursename,sem,department);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	
	
	
	
}
