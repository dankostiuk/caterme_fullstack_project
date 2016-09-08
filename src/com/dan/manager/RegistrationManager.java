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
 * Carries out ORM-related interactions for user registration.
 * 
 * @author dan
 */
public class RegistrationManager extends AbstractManager<Void> {
	
	private UserManager _userManager;
	
	private static final String MD5 = "MD5";
	
	public RegistrationManager() {
		super(null);
		_userManager = new UserManager();
	}
	
	/**
	 * Encrypts incoming user password and saves the user.
	 * @param user The user to save.
	 */
	public void registerUser(User user) {
		
		String password;
		
		try {
			password = PasswordUtil.generateMd5Checksum(user.getPassword());
		} catch (LoginException e) {
			return;
		}
		
		user.setPassword(password);
		_userManager.saveUser(user);
	}
}
