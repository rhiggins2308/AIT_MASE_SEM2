package mase.tus.dss.lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.KeyManagementException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class Lab_6 {

	/* Q1
	 * Write a Java program (notes) to
	 * - Digitally sign some text
	 * - Verify the text and digital signature
	 */
	public void Q1() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey privateKey = pair.getPrivate();
			PublicKey publicKey = pair.getPublic();
			
			// sending the data
			Signature dsa = Signature.getInstance("SHA256withDSA");
			dsa.initSign(privateKey);
			byte[] message = "Sending Data".getBytes();
			dsa.update(message);
			byte[] sig = dsa.sign();
			// receiving the data and verifying
			dsa.initVerify(publicKey);
			boolean verifies = dsa.verify(sig);
			System.out.println("signature verifies: " + verifies);
		} catch( Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Q2
	 * Write a Java program to
	 * - Digitally sign some text
	 * - Write the text to a file.
	 * - Write the public key to another file.
	 * - Write the digital signature to yet another file
	 * 
	 * Write a second Java program to
	 * - Read the text from a file.
	 * - Read the public key from a file.
	 * - Read the digital signature from a file.
	 * - Verify the text and digital signature.
	 */
	
	static void writeToFile(String filename, Object object) throws Exception {
	    FileOutputStream fout = new FileOutputStream(filename);
	    ObjectOutputStream oout = new ObjectOutputStream(fout);
	    oout.writeObject(object);
	    oout.close();
	  }
	
	public void Q2() {
		try {
			System.out.println();
		} catch (Exception e) {
			
		}
	}
	
	
	
	public void keyStorePrintCert() {
		FileInputStream fr;
		try {
			fr = new FileInputStream("paul.cer");
			CertificateFactory cf;
			cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			System.out.println("Read in the following certificate:");
			System.out.println("\tCertificate for: " + c.getSubjectDN());
			System.out.println("\tCertificate issued by: " + c.getIssuerDN());
			System.out.println("\tThe certificate is valid from "
			+ c.getNotBefore() + " to " + c.getNotAfter());
			System.out.println("\tCertificate SN# " + c.getSerialNumber());
			System.out.println("\tGenerated with " + c.getSigAlgName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void keyStoreServer() {
		try {
			char[] KSPASSWORD = "kkkk".toCharArray();
			char[] PASSWORD = "1234".toCharArray();
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream("keystore"), KSPASSWORD);
			KeyManagerFactory keyManagerFactory = KeyManagerFactory
			.getInstance("SunX509");
			keyManagerFactory.init(keyStore, PASSWORD);
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
			ServerSocketFactory ssFactory = sslContext.getServerSocketFactory();
			
			int port = 443;
			ServerSocket ss = ssFactory.createServerSocket(port);
			Socket socket = ss.accept();
			// Create streams to securely send and receive data to the client
			InputStream in = socket.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(in));
			String name = r.readLine();
			OutputStream o = socket.getOutputStream();
			PrintWriter p = new PrintWriter(o);
			p.println("Hello " + name);
			p.close();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void keyStoreSSLClient()
}
