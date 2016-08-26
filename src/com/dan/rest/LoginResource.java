package com.dan.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.plexus.util.StringUtils;

import com.dan.manager.LoginManager;

/**
 * JAX-RS API endpoints for logging in.
 * 
 * @author dan
 */
@Path("/login")
public class LoginResource {

	LoginManager _loginManager;
	
	public LoginResource() {
		_loginManager = new LoginManager();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateLogin(@QueryParam("email") String email, 
			@QueryParam("password") String password) {
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return Response.status(400)
				.entity("Bad Request. Ensure non-emtpy email and passwords are supplied.")
				.build();
		}
		
		Boolean isValid = _loginManager.processLogin(email, password);
		
		return Response.status(200).entity(isValid).build();
	}

}
