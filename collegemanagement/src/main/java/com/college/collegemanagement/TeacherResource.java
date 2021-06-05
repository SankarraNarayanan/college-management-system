package com.college.collegemanagement;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("teacher")
public class TeacherResource {

	TeacherRepository repo = new TeacherRepository();
	Hash hash = new Hash();
	@POST
	@Path("reset")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response assignmnet(@FormParam("password") String password , @CookieParam("username") String username) throws URISyntaxException
	{
		
		password = hash.encrypt(password);
		System.out.println(password);
		repo.resetPassword(password,username);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@POST
	@Path("addstudent")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response adddept(@CookieParam("username") String t_id,@CookieParam("semester") String semester,@CookieParam("department") String department,
			@FormParam("username") String username,@FormParam("name") String name,
			@FormParam("password") String password) throws URISyntaxException
	{
		int sem = Integer.parseInt(semester);
		
		password = hash.encrypt(password);
		System.out.println(password);
		repo.addStudent(t_id,username,name,password,sem,department);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response studupdate(@FormParam("username") String username,@FormParam("semester") String semester) throws URISyntaxException
	{
		int sem = Integer.parseInt(semester);
		repo.studUpdate(username,sem);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	@POST
	@Path("enroll")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response enroll(@FormParam("course_id") String course_id,
			@FormParam("course_name") String course_name,@FormParam("semester") String semester,
			@FormParam("department") String department) throws URISyntaxException
	{
		int sem = Integer.parseInt(semester);
		repo.Enroll(course_id,course_name,sem,department);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@GET
	@Path("getstudent")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewstudent(@CookieParam("username") String username)
	{
		System.out.println(username);
		List<Student> students = repo.getStudent(username);
		return students;
	
	}
	
	@POST
	@Path("deletestudent")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response adddept(@FormParam("username") String username,@CookieParam("username") String t_username) throws URISyntaxException
	{
		
		repo.deleteStudent(username,t_username);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	@POST
	@Path("assignment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response assignmnet(@FormParam("courseid") String courseid,@CookieParam("semester") String semester,@CookieParam("department") String department,@FormParam("course") String course,
			@FormParam("title") String title,@FormParam("description") String description,
			@FormParam("duedate") Date due,@CookieParam("username") String username) throws URISyntaxException
	{
		System.out.println(due);
		int sem = Integer.parseInt(semester);
		repo.assignment(username,sem,course,courseid,department,title,description,due);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	@GET
	@Path("not-finished-students/{w_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> nfa(@PathParam("w_id") int w_id)
	{
		List<Student> students = repo.Nfa(w_id);
		return students;
		
		
	}
	@GET
	@Path("assignments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> assignments(@CookieParam("username") String username)
	{
		List<Assignment> assignments = repo.allAssignments(username);
		return assignments;
		
		
	}
	@GET
	@Path("getcourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> getcourse(@CookieParam("username") String username)
	{
		List<Courses> courses = repo.getCourse(username);
		 return courses;
	}	
}
