package com.dan.manager;

import com.dan.entity.User;


/**
 * Carries out ORM-related interactions for logging in.
 * 
 * @author dan
 */
public class LoginManager extends AbstractManager<Void> {
	
	private UserManager _userManager;
	
	public LoginManager() {
		super(null);
		_userManager = new UserManager();
	}
	
	public boolean processLogin(String email, String password) {
		
		//TODO: do a lookup to verify the given email and password exists in the db
		// if it does: return true, increase loginCount + update lastLogin
		// if not, return false, log the attempt
		User user = _userManager.findUser(email);
		
		if (user.getPassword().equals(password))
		{
			//_userManager.updateUser(user.getId(), user);
			
			return true;
		}
		
		return false;
	}
}
