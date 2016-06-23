package com.dan.rest;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dan.entity.User;
import com.dan.manager.UserManager;

/**
 * JAX-RS API endpoints.
 * 
 * @author dan
 */
@Path("/user")
public class UserResource {

	UserManager _userManager;
	
	public UserResource() {
		_userManager = new UserManager();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("id") Integer id) {
		if (id == null)
		{
			return Response.status(400).build();
		}
		
		User user = null;
		try {
			user = _userManager.getUser(id);
		} catch (NamingException e) {
			return Response.status(500).entity("Error occured while retrieving user.").build();
		}
		
		if (user == null) {
			return Response.status(404).entity("User could not be found.").build();
		}
		
		return Response.status(200).entity(user).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		_userManager.createUser(user);
		return Response.status(200).build();
	}
}
