package com.college.collegemanagement;

import java.net.URISyntaxException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("student")
public class StudentResource {
	Hash hash = new Hash();
	StudentRepository repo = new StudentRepository();
	@POST
	@Path("reset")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response assignmnet(@FormParam("password") String password , @CookieParam("username") String username) throws URISyntaxException
	{
		password = hash.encrypt(password);
		repo.resetPassword(password,username);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/StudentHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
		
	}
	
	@GET
	@Path("courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> courses(@CookieParam("username") String username)
	{
		List<Courses> courses = repo.allCourses(username);
		return courses;
		
		
	}
	@GET
	@Path("assignments/{courseid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> assignments(@CookieParam("username") String username,@PathParam("courseid") String courseid)
	{
		List<Assignment> assignments = repo.allAssignments(username,courseid);
		return assignments;
		
		
	}
}
