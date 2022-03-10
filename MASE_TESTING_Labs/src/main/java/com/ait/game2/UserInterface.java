package com.ait.game2;

import java.util.Scanner;

public class UserInterface {

	public void outputMessage(String message) {
		System.out.println(message);
	}

	public String getInput() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

}
