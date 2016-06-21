package com.dan.rest;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.dan.entity.User;
import com.dan.manager.UserManager;
import com.google.gson.Gson;

@Path("/user")
public class UserResource {

	UserManager _userManager;
	
	public UserResource() {
		_userManager = new UserManager();
	}
	
	@GET
	@Produces("application/json")
	public Response getUser(@QueryParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		
		User user = null;
		try {
			user = _userManager.getUser(id);
		} catch (NamingException e) {
			// TODO: handle this exception
		}
		
		String fName = user.getFirstName();
		String lName = user.getLastName();
		
		jsonObject.put("fName", fName);
		jsonObject.put("lName", lName);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response createUser(@QueryParam("json") String json) {
		
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		
		_userManager.createUser(user);
		
		return Response.status(200).build();
	}
}
