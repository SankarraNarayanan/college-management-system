package com.college.collegemanagement;

import java.net.URISyntaxException;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin")
public class AdminResource {
	AdminRepository repo = new AdminRepository();
	
	
	@POST
	@Path("addmanagement")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addManagement(@FormParam("collegeid") String collegeid, 
            @FormParam("collegename") String collegename,
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("location") String location
			) throws URISyntaxException
	{
		int collegeId=Integer.parseInt(collegeid);
		String res = repo.createManagement(collegeId,collegename,username,password,
			location);
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/AdminHomepage.html");
		return Response.temporaryRedirect(reslocation).build();
	}
	
	@GET
	@Path("managements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Management> viewmanagement()
	{
			
			return repo.viewmanagement();

	}	
	
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response delete(@FormParam("collegeid") String collegeid,@FormParam("edit") String editaction,
			@FormParam("delete") String deleteaction,@FormParam("collegename") String collegename,
			@FormParam("location") String location)throws URISyntaxException
	{
		java.net.URI reslocation = new java.net.URI("http://localhost:8080/collegemanagement/AdminHomepage.html");
		int Cid = Integer.parseInt(collegeid);
		System.out.println(Cid);
		System.out.println(editaction+" "+deleteaction+" "+collegename+" "+location);
		
		if(editaction!=null)
		{
			System.out.println("wel");
			repo.editmanagement(Cid,collegename,location);
		}
		else
		{
			if(deleteaction!=null)
			{
				repo.deletemanagement(Cid);
			}
		}
		
		return Response.temporaryRedirect(reslocation).build();
	}
}
