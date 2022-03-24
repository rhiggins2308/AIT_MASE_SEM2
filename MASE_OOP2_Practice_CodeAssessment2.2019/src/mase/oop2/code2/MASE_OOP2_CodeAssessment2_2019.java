package mase.oop2.code2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MASE_OOP2_CodeAssessment2_2019 {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		final int STREAMS = 1, LOCALISATION = 2, EXIT = 3;
        String userContinue = "y";

        while (userContinue.equalsIgnoreCase("y")) {
            switch (userChoice()) {
                case STREAMS:
                    streams();
                    break;
                case LOCALISATION:
                    localisation();
                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    userContinue = "n";
                    break;
                default:
                    System.out.println("Unknown entry : ");
                    break;
            }
        }
	}

	public static void streams() {
		 
	}
	
	public static void localisation() {
		
	}
	
	public static int userChoice() {
		System.out.println("\nWhat do you want to do ?");
        System.out.println("1. Streams");
        System.out.println("2. Localisation");
        System.out.println("3. Exit");
        System.out.print("Enter choice --> ");
        return sc.nextInt();
	}
}

class Worker {
	
}