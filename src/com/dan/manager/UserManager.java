package com.dan.manager;

import javax.naming.NamingException;

import com.dan.entity.User;

public class UserManager extends AbstractManager<User> {
	
	public UserManager() {
		super(User.class);
	}
	
	public User getUser(int id) throws NamingException {
		User user = readTransaction(id);
		
		return user;
	}
	
	public void createUser(User user) {
		writeTransaction(user);
	}
}
