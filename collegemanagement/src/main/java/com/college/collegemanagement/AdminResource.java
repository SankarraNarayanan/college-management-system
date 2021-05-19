package com.college.collegemanagement;

import java.net.URISyntaxException;
import java.util.List;
import jakarta.ws.rs.Consumes;
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
	@Produces(MediaType.APPLICATION_JSON)
	public String addManagement(Management m1)
	{
		repo.createManagement(m1);
		if(m1!=null) return "succesfully added";
		
		return "failed to add";
		
	}
	
	@GET
	@Path("managements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> viewmanagement()
	{
		List<String> managements = repo.viewmanagement();
		if(managements.size()>0)
		{
			return managements;
		}
		
		managements.add("No Managements found");
		return managements;
	}	
}
