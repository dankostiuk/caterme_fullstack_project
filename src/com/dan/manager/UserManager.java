package com.dan.manager;

import java.util.List;

import javax.naming.NamingException;

import com.dan.entity.User;

/**
 * Carries out User ORM operations.
 * 
 * @author dan
 */
public class UserManager extends AbstractManager<User> {
	
	public UserManager() {
		super(User.class);
	}
	
	public User getUser(int id) throws NamingException {
		User user = readTransaction(id);
		
		return user;
	}
	
	public List<User> getAll() {
		List<User> users = readAllTransaction();
		return users;
	}
	
	public void createUser(User user) {
		writeTransaction(user);
	}
}
