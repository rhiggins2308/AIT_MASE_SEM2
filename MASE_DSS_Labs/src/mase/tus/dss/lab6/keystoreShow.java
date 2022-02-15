package mase.tus.dss.lab6;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class keystoreShow {
	  public static void main(String[] args) throws Exception {
	    String keystoreFilename = "keystore";
	    
	    char[] password = "file".toCharArray(); // file password
	    String alias = "robbie";

	    FileInputStream fIn = new FileInputStream(keystoreFilename);
	    KeyStore keystore = KeyStore.getInstance("JKS");

	    keystore.load(fIn, password);

	    Certificate cert = keystore.getCertificate(alias);

	    System.out.println(cert);
	  }
}
