package ite.librarymaster.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
	
	static String getMD5(String plainPassword) throws NoSuchAlgorithmException{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plainPassword.trim().getBytes());
		return new BigInteger(1,m.digest()).toString(16);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		System.out.println(getMD5("password"));
	}
	

}
