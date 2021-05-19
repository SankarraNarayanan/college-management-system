package com.college.collegemanagement;
import java.net.URISyntaxException;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class User_redirect {
    User_repository repo = new User_repository();
	
    @POST
	@Path("if_u")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response check(@FormParam("username") String username, 
			              @FormParam("password") String password) throws URISyntaxException
	{
		java.net.URI location=null;
		if(username.equals("admin")& password.equals("admin"))
		{
			  location = new java.net.URI("http://localhost:8080/collegemanagement/AdminHomepage.html");
			 
		}
		else if(!username.equals("admin")) {
			String user = repo.getManagement(username);
			
			if(user=="management")
			{
				 location = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.jsp");
			}
			else if(user=="teacher")
			{
				 location = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
			}
			else if(user=="student")
			{
				location = new java.net.URI("http://localhost:8080/collegemanagement/StudentHomepage.html");
			}
			else if(user=="")
			{
			 location = new java.net.URI("http://localhost:8080/collegemanagement/error.jsp");
			}
		}
		
		 return Response.temporaryRedirect(location).build();
	}
}
