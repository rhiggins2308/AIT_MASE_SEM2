package mase.tus.dss.lab2;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Lab_2 {

/* Q1. Write a Java program to find the SHA-256 hash of a String. 
 * Print out the Base64 encoding of the hash value.
 * Show that if the String changes by just 1 character, the digest value changes completely.
*/
	public void Q1() {
		String s = "Hello there";
		System.out.println(s);
		byte[] sBytes = s.getBytes();

		String encodedString = Base64.getEncoder().encodeToString(sBytes);
		System.out.println("s is: " + s + " Encoded: " + encodedString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		System.out.println("Encoded: " + encodedString + "\nDecoded: " + new String(decodedBytes));
	}
	
/*
 * Q2. Write a program to find the SHA-256 hash of a file.
 * Print out the Base64 encoding of the hash value.
 * Use the MessageDigest method void update(byte[] input, int offset, int len)
 * and the InputStream method int read(byte[] b)
 * 
 * Use:-
 * InputStream is = new FileInputStream("data/test.txt");
 * byte[] buffer = new byte[64];
 * int bytesRead = 0;
 * while ((bytesRead = is.read(buffer)) > 0) {System.out.println(bytesRead);}
*/
	public void Q2() throws IOException, NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		InputStream is = new FileInputStream("data/test.txt");
		byte[] buffer = new byte[64];
		int bytesRead = 0;
		
		while ((bytesRead = is.read(buffer)) > 0) {
			System.out.println(bytesRead);
		}

		md.update(buffer, 0, 0); // 
		
	}
	
	public void Q3() {
		
	}

	public void Q4() {
	
	}

	public void Q5() {
	
	}

	public void Q6() {
	
	}	
}
	
/*

Q3.
Look up the documentation on Apache Commons Codec API . (http://commons.apache.org/codec/).
Write a program to hash some text using this library and different hashing algorithms.
Q4.
Write a program to hash a file using the Apache Commons Codec library.
 */
	
	
