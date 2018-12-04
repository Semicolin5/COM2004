package src.model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class CryptoModule {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//This is just used for testing the functions
		String salt = generateSalt();
		String hashPass = hashPassword("cat",salt);
		
		System.out.println(salt);
		System.out.println(hashPass);
	}
	
    /**
     * hashPassword, given a password and a salt, returns a hashed password.
     * @param password is the String of the users password.
     * @param salt is the String of the users salt.
     * @return String of the users hashed password (64 chars) or null if we have an exception (should never happen).
     * */
	public static String hashPassword(String password, String salt) {
		//Get our SHA-256 digest
		MessageDigest SHA256;
		try {SHA256 = MessageDigest.getInstance("SHA-256");}
		catch (NoSuchAlgorithmException e) {/*Can print stack trace for debugging instead return null*/ return null;}
		
		//Salt password and hash
		String saltedPass = password + salt;
		byte[] hashedPass = SHA256.digest(saltedPass.getBytes(StandardCharsets.UTF_8));
		
		//Convert to Hexadecimal string
		String output = "";
		for(int i = 0; i < hashedPass.length; i++) {
			output = output + String.format("%02X", hashedPass[i]);
		}
		return output;
	}

    /**
     * generateSalt, generates a random salt.
     * @return String of the randomly generated salt (32 chars)
     * */
	public static String generateSalt() {
		//generate random byte array (raw salt)
		Random random = new SecureRandom();
		byte[] rawSalt = new byte[16];
		random.nextBytes(rawSalt);
		
		//turn raw salt into a string
		String salt = "";
		for(int i = 0; i < rawSalt.length; i++) {
			salt = salt + String.format("%02X", rawSalt[i]);
		}
		
		return salt;
	}
}
