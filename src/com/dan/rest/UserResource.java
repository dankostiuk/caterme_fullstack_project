package com.dan.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.maven.shared.utils.StringUtils;

import com.dan.entity.User;
import com.dan.manager.UserManager;

/**
 * JAX-RS API endpoints for the User entity.
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Integer id) {
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUser(@QueryParam("username") String username) {
		if (StringUtils.isEmpty(username))
		{
			return Response.status(400).build();
		}
		
		User user;
		try
		{
			user = _userManager.findUser(username);
		} catch (EntityNotFoundException e) {
			return Response.status(404).entity("User could not be found.").build();
		}

		
		return Response.status(200).entity(user).build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		List<User> users = _userManager.getAll();
		
		if (users == null || users.size() == 0) {
			return Response.status(404).entity("Users could not be found.").build();
		}
		
		return Response.status(200).entity(users).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		_userManager.saveUser(user);
		return Response.status(200).build();
	}
}
