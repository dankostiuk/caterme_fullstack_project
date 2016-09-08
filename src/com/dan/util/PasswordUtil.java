package com.dan.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class PasswordUtil {
	
	// String to denote MD5 encryption
	private static final String MD5 = "MD5";
	
	/**
	 * Generates and returns an MD5 checksum for the given password.
	 * @param plaintextPassword The plaintext password.
	 * @return The md5Checksum of the password.
	 * @throws LoginException If an error occurs.
	 */
	public static String generateMd5Checksum(String plaintextPassword) throws LoginException {
		
		String md5Checksum = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance(MD5);
			md5Checksum = (new HexBinaryAdapter()).marshal(md5.digest(plaintextPassword.getBytes())).toLowerCase();	
		} catch (NoSuchAlgorithmException e) {
			throw new LoginException("An error occured while processing password.");
		}
		return md5Checksum;
	}
}
