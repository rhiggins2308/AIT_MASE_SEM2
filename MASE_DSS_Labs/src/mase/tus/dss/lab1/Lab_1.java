package mase.tus.dss.lab1;

import java.security.SecureRandom;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import java.util.Random;
import java.util.Set;

public class Lab_1 {
	// Q1
	public void printThreeRandNumsMathRandom() {
		System.out.println(Math.random());
		System.out.println(Math.random());
		System.out.println(Math.random());
	}
	
	// Q2
	public void printThreeRandNumsUtilRandom() {
		
		//Random rand = new Random(0);
		Random rand = new Random();
		//rand.setSeed(0);
		
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextDouble());
	}
	
	// Q3
	public void printUsingUtilRandom() {
		//Random rand = new Random(0);
		Random rand = new Random();
		//rand.setSeed(0);System.out.println(rand.nextInt());
		System.out.println(rand.nextInt());
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextInt(100));
	}
	
	// Q4
	public void printRandIntSecureRandom() {
		SecureRandom rand = new SecureRandom();
		
		System.out.println(rand.nextInt(1_000));
		System.out.println(Hex.encodeHexString((rand.generateSeed(20))));
	}
	
	// Q5
	public void setSeedAndPrint() {
		SecureRandom sec = new SecureRandom();
		sec.setSeed(20);
		System.out.println(sec.nextInt(1_000));
	}
	
	// Q6
	public void LinConGen() {
		int seed = 5;
		int mod = 7;
		int multiplier = 3;
		int inc = 3;
		int prev = seed;
		
		for (int i = 0; i < 20; i++) {
			int rand = (prev * multiplier+inc) % mod;
			prev = rand;
			System.out.println(rand + " ");
		}	
	}
	
	// Q7
	public void listProviders() {
		
		final Set<String> algos = Security.getAlgorithms("SecureRandom");
		
		for (String algo : algos) {
			System.out.println(algo);
		}
		
		final String defaultAlgo = new SecureRandom().getAlgorithm();
		System.out.println("default : " + defaultAlgo);
	}
}
