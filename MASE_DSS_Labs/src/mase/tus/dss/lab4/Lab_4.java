package mase.tus.dss.lab4;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Lab_4 {

	/* Q1
	 * Write a Java program (notes) to
	 * - encrypt some text with AES (TwoFish, Serpent)
	 * - then decrypt it
	 */
	public void lab4Q1() {
		try {
			// String ALGORITHM = "DES" ;
			String ALGORITHM = "AES" ;
			// String ALGORITHM = "BlowFish";
			
			KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
			SecretKey key = keygen.generateKey();
			Cipher eCipher = Cipher.getInstance(ALGORITHM);
			
			// Initialize the cipher for encryption
			eCipher.init(Cipher.ENCRYPT_MODE, key);
			
			String s = "This is just an example";
			System.out.println("Clear text: " + s);
			byte[] cleartext = s.getBytes();
			System.out.println("Length of clear text " + cleartext.length);
			
			// Encrypt the cleartext
			byte[] ciphertext = eCipher.doFinal(cleartext);
			System.out.println("Length of cipher text " + ciphertext.length);
			System.out.println("Cipher text: " + new String(ciphertext));
			
			// Decrypt
			Cipher dCipher = Cipher.getInstance(ALGORITHM);
			dCipher.init(Cipher.DECRYPT_MODE, key);
			
			// Decrypt the ciphertext
			byte[] clearText1 = dCipher.doFinal(ciphertext);
			String text = new String(clearText1);
			System.out.println("Decrypted text: " + text);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Q2
	 * Write a Java class Employee with fields name, address telNo.
	 * Write a Java program to
	 * - instantiate an Employee object
	 * - create a SealedObject containing the Employee object, encrypted with some Cipher
	 * //
	 * - Extract the object from the SealedObject using another cipher (initialized for decryption)
	 * - Print out the contents of the Employee object.
	 */
	public void lab4Q2() {
		try {
			// String ALGORITHM = "DES" ;
			String ALGORITHM = "AES" ;
			// String ALGORITHM = "BlowFish";

			KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
			SecretKey key = keygen.generateKey();
			Cipher eCipher = Cipher.getInstance(ALGORITHM);

			// Initialize the cipher for encryption
			eCipher.init(Cipher.ENCRYPT_MODE, key);

			// Seal (encrypt) the object
			SealedObject so = new SealedObject(new Employee("007", "England", "087 007007"), eCipher);

			// can now store the object for example.

			// ///////////////////////////
			// Decrypt

			// Prepare the decrypter
			Cipher dCipher = Cipher.getInstance(ALGORITHM);
			dCipher.init(Cipher.DECRYPT_MODE, key);

			// Unseal (decrypt) the class
			Employee o = (Employee) so.getObject(dCipher);
			System.out.println("Employee " + o.getName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


		
	}
	
	/* Q3a
	 * Write a Java program to
	 * - instantiate an Employee object
	 * - create a SealedObject containing an Employee object, encrypted with some Cipher
	 * - save the SealedObject to a file (“data/sealedObject.dat”)
	 * - save the Cipher key to a file (“data/secretKey”)
	 * 
	 * You can use
	 * private static void writeToFile(String filename, Object object) throws Exception {
	 * 		FileOutputStream fout = new FileOutputStream(new File(filename));
	 * 		ObjectOutputStream oout = new ObjectOutputStream(fout);
	 * 		oout.writeObject(object);
	 * 		oout.close();
	 * }
	 */
	
	/* Q3b
	 * Write a second Java program to
	 * - read the key from file
	 * - read the SealedObject from file
	 * - extract the Employee object from the SealedObject.
	 * - print out the text stored in the Employee object.
	 * 
	 * static Object readFromFile(String filename) throws Exception {
	 * 		FileInputStream fin = new FileInputStream(filename);
	 * 		ObjectInputStream oin = new ObjectInputStream(fin);
	 * 		Object object = oin.readObject();
	 * 		oin.close();
	 * 		return object;
	 * }
	 */

