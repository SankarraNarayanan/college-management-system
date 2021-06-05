package com.college.collegemanagement;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("user")
public class User_redirect {
    User_repository repo = new User_repository();
    Hash hash = new Hash();
    @POST
	@Path("if_u")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response check(@FormParam("username") String username, 
			              @FormParam("password") String password ,@CookieParam("lastVisited") String usertype) throws URISyntaxException
	{
    	password = hash.encrypt(password);
    	System.out.println(password);
    	String user="";
    	NewCookie teachersem=null;
    	NewCookie teacherdept=null;
    	
    	List<String> detail=new ArrayList<>();
    	java.net.URI location=null;
		user = repo.getAdmin(username,password);
		System.out.println(user);
			if(user=="admin")
			{
				 location = new java.net.URI("http://localhost:8080/collegemanagement/ManagementHomepage.html");
			}
			else if(user=="teacher")
			{
				detail = repo.teacherdetails(username);
				teachersem = new NewCookie("semester",detail.get(0),"/",null,1,"no comment",1073741823 ,true);
				 teacherdept = new NewCookie("department",detail.get(1),"/",null,1,"no comment",1073741823 ,true);
				
				 location = new java.net.URI("http://localhost:8080/collegemanagement/TeacherHomepage.html");
			}
			else if(user=="student")
			{
				location = new java.net.URI("http://localhost:8080/collegemanagement/StudentHomepage.html");
			}
			else if(user=="")
			{
				location = new java.net.URI("http://localhost:8080/collegemanagement/");
				
			}
			
			 
			NewCookie lastVisited = new NewCookie("lastVisited",      
	            user,      
	            "/",      
	            null,      
	            1,      
	            "no comment",      
	            1073741823 ,    
	            true);
	NewCookie userName = new NewCookie("username",      
            username,      
            "/",      
            null,      
            1,      
            "no comment",      
            1073741823 ,      
            true );
		if(user=="teacher")
		{
			return Response.seeOther(location)
		               .cookie(lastVisited).cookie(userName).cookie(teachersem).cookie(teacherdept)
		               .build();
		}
		return Response.seeOther(location)
	               .cookie(lastVisited).cookie(userName)
	               .build();
	}
    
    @Produces(MediaType.TEXT_PLAIN)
	private String errmsg() {
		return "no user";
	}
    
    
}
