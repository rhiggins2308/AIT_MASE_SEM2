package mase.tus.dss.lab3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Lab_3 {

	/* Q1
	 * Write a Java program (notes) to
	 * - Get an instance of HmacSHA256
	 * - Use it, along with a secret key, to create a HMAC signature for a message.
	 * - Hash it again and check the equality of the two signatures.
	 */	
	public void lab3Q1() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = kg.generateKey();
			
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			byte[] result = mac.doFinal("Hi there".getBytes());
			System.out.println(result.length);
			
			// Receiver
			Mac mac2 = Mac.getInstance("HmacSHA256");
			mac2.init(sk);
			byte[] result2 = mac.doFinal("Hi there".getBytes());
			
			System.out.println("Equality check: " + Arrays.equals(result, result2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Q2 & Q3
	 * We are going to simulate a sender (Q2) and receiver (Q3). Instead of sending
	 * directly, Q2 will put stuff into files and Q3 will read stuff from files.
	 */
		
	/* Q2
	 * Write a Java program to
	 * - Generate a HmacSHA256 secret key.
	 * - Store it to file called “data/secretKey”
	 * - Write a String to “data/sendText.txt”
	 * - Calculate the HMAC for the String Base64 and write it to the file “data/hmac”
	 * 
	 * You can use the method
	 * static Object readFromFile(String filename) throws Exception {
	 * 		FileInputStream fin = new FileInputStream(filename);
	 * 		ObjectInputStream in = new ObjectInputStream(fin);
	 * 		Object object = in.readObject();
	 * 		in.close();
	 * 		return object;
	 * 	}
	 */
	static Object readFromFile(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fin);
		Object object = in.readObject();
		in.close();
		return object;
	}
	
	static void writeToFile(String filename, Object obj) throws Exception {
		FileOutputStream fon = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fon);
		out.writeObject(obj);
		out.close(); 
	}
	
	public void lab3Q2() {
		
		try {
			String message = "This is the data I am sending";
			
			// write secret key
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = kg.generateKey();
			writeToFile("data/secretKey", sk);
			
			// write message
			writeToFile("data/sendText.txt", message);
			
			//write hmac
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			byte[] hmac = mac.doFinal(message.getBytes());
			writeToFile("data/hmac", hmac);
			String encodedHmac = Base64.getEncoder().encodeToString(hmac);
			
			System.out.println(hmac.length);
			System.out.println("Base64 encoded message digest " + encodedHmac);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/* Q3 (w. Q2)
	 * Write a second Java program to
	 * - Read the secret key from the file “data/secretKey”
	 * - Read the text from the file “data/sendText”
	 * - Read the HMAC from the file “data/hmac”
	 * - Calculate the HMAC for the text.
	 * - Compare it with the value received.
	 * - [If it compares, the client is sure of the integrity of the sent text.
	 */
	public void lab3Q3() throws Exception {
		// read secret key
		SecretKey sk = (SecretKey) readFromFile("data/secretKey");
		byte[] sentHmac = (byte[])readFromFile("data/hmac");
		String sendText = (String)readFromFile("data/sendText.txt");
		
		// calculate hmac
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(sk);
		byte[] myHmac = mac.doFinal(sendText.getBytes());
		
		// check hmac
		System.out.println("Equality Check: " + Arrays.equals(sentHmac, myHmac));
	}
}
