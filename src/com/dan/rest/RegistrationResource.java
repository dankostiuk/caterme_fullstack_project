package com.dan.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.maven.shared.utils.StringUtils;

import com.dan.entity.User;
import com.dan.manager.RegistrationManager;


/**
 * JAX-RS API endpoints for registration.
 * 
 * @author dan
 */
@Path("/register")
public class RegistrationResource {

	RegistrationManager _registrationManager;
	
	public RegistrationResource() {
		_registrationManager = new RegistrationManager();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User user) {	
		if (user == null
				|| StringUtils.isEmpty(user.getUsername())
				|| StringUtils.isEmpty(user.getPassword())
				|| StringUtils.isEmpty(user.getFirstName())
				|| StringUtils.isEmpty(user.getLastName())
				|| StringUtils.isEmpty(user.getAddress())
				|| user.getPhone() == null) {
			return Response.status(500).build();
		}
		
		try {
			_registrationManager.registerUser(user);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

}
