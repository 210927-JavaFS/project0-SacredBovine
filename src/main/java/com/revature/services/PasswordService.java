package com.revature.services;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  

public class PasswordService {
	
	 public  byte[] getSHA(String pass) throws NoSuchAlgorithmException  
	    {  
		 	MessageDigest hasher = MessageDigest.getInstance("SHA-256");
		 	return hasher.digest(pass.getBytes(StandardCharsets.UTF_8)); 
	    }
	 public String toHexString(byte[] hashedPass) {
		 BigInteger foo = new BigInteger(1,hashedPass);
		 
		 StringBuilder hexPass = new StringBuilder(foo.toString(16));
		 
		 while(hexPass.length() < 32) {
			 hexPass.insert(0,'0');
		 }
		 return hexPass.toString();
	 }

}