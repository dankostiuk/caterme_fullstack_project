package com.dan.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.dan.entity.User;
import com.dan.util.PasswordUtil;


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
	
	/**
	 * Check if password is correct for the given email and if so, return true.
	 * @param email The email of the user logging in.
	 * @param password The password of the user logging in.
	 * @return boolean True if email+password combination is valid, false otherwise.
	 */
	public boolean processLogin(String email, String password) {
		// if it does: return true, increase loginCount + update lastLogin
		// if not, return false, log the attempt
		User user = null;
		try {
			user = _userManager.findUser(email);	
		} catch (EntityNotFoundException ex) {
			return false;
		}
		
		String md5Checksum;
		try {
			md5Checksum = PasswordUtil.generateMd5Checksum(password);
		} catch (LoginException e) {
			return false;
		}
		
		// TODO: do some sort of better auth on the user
		if (user.getPassword().equals(md5Checksum)) {	

			// update login count and save user
			user.setLoginCount((user.getLoginCount() == null ? 0 : user.getLoginCount()) + 1);
			_userManager.saveUser(user);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Generate login token to store in the client cookie.
	 * @return The generated login token.
	 */
	private String generateLoginToken() {
		return UUID.randomUUID().toString();
	}

}
