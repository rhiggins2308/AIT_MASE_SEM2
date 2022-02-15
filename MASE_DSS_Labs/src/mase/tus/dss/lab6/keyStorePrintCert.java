package mase.tus.dss.lab6;

import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class keyStorePrintCert {
	public static void main(String args[]) {
		try {
			FileInputStream fr = new FileInputStream("robbie.cer");
			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			System.out.println("Read in the following certificate:");
			System.out.println("\tCertificate for: " + c.getSubjectDN());
			System.out.println("\tCertificate issued by: " + c.getIssuerDN());
			System.out.println("\tThe certificate is valid from "
					+ c.getNotBefore() + " to " + c.getNotAfter());
			System.out.println("\tCertificate SN# " + c.getSerialNumber());
			System.out.println("\tGenerated with " + c.getSigAlgName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
