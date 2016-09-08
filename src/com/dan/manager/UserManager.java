package com.dan.manager;

import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityNotFoundException;

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
	
	/**
	 * Get the user for the given id.
	 * @param id The id of the user to find.
	 * @return The user to find.
	 * @throws NamingException If an error occurs.
	 */
	public User getUser(int id) throws NamingException {
		User user = readTransaction(id);
		
		return user;
	}
	
	/**
	 * Find user by username
	 * @param username The username of the user to find.
	 * @return The user that matches the given username.
	 * @throws EntityNotFoundException If an error occurs.
	 */
	public User findUser(String username) throws EntityNotFoundException {
		User user = findTransaction("username", username);
		
		return user;
	}
	
	/**
	 * Gets all users.
	 * @return All users.
	 */
	public List<User> getAll() {
		List<User> users = readAllTransaction();
		return users;
	}
	
	/**
	 * Saves the user. If the user has no id, create the user.
	 * Otherwise, update fields from incoming object and persist.
	 * @param user The user to save.
	 */
	public void saveUser(User user) {
		
		if (user.getId() == null || user.getId() == -1) {
			writeTransaction(user);
		} else {
			User currentUser = readTransaction(user.getId());
			
			if (user.getFirstName() != null) {
				currentUser.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				currentUser.setLastName(user.getLastName());
			}
			if (user.getPhone() != null) {
				currentUser.setPhone(user.getPhone());
			}
			if (user.getLoginCount() != null) {
				currentUser.setLoginCount(user.getLoginCount());
			}
			
			writeTransaction(currentUser);
		}
		
	}
}
