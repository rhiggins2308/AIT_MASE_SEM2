package com.ait.game;

//import java.util.Scanner;

import com.ait.game.Player;
import com.ait.game2.UserInterface;

public class Game {
	//private Scanner sc;
	private Player player1;
	private Player player2;
	private int numDiceRolls;
	static final int SCORE_TO_WIN = 20;

	UserInterface userInterface;
	PairOfDice pairOfDice;
	
	public Game(UserInterface userInterface, PairOfDice pairOfDice) {
		this.userInterface = userInterface;
		this.pairOfDice = pairOfDice;
	}
	
	public void start() {
		numDiceRolls = 0;
		// display a welcome message
		userInterface.outputMessage("Welcome to teh Dice Roller application\n");
	/*	System.out.println("Welcome to the Dice Roller application");
		System.out.println();
		sc = new Scanner(System.in);
	*/
		String player1Name;
		String player2Name;
		userInterface.outputMessage("Please enter Player 1 name: ");
		player1Name = userInterface.getInput();
		userInterface.outputMessage("Please enter Player 2 name: ");
		player2Name = userInterface.getInput();
	/*	System.out.println("Please enter player one name: ");
		player1Name = sc.next();
		System.out.println("Please enter player two name: ");
		player2Name = sc.next();
	*/
		
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);

		
		// direct dependency on PairOfDice class
		PairOfDice dice = new PairOfDice(); 

		/*
		 * difficult to test
		 * required a stub class that needed to be changed with new values for each test
		 */

		userInterface.outputMessage("Roll the dice? (y/n): ");
		String choice = userInterface.getInput().toLowerCase();
	/*	System.out.println("Roll the dice? (y/n): ");
		String choice = sc.next().toLowerCase();
	*/
		
		while (choice.equals("y")) {
			numDiceRolls++;
			
			userInterface.outputMessage("");
			userInterface.outputMessage("Round " + numDiceRolls + ": ");
			userInterface.outputMessage("Rolling dice for player one ");
		/*	System.out.println("");
			System.out.println("Round " + numDiceRolls + ": ");
			System.out.println("Rolling dice for player one ");
		*/
			dice.roll();
			userInterface.outputMessage("Die 1 is " + dice.getValue1());
			userInterface.outputMessage("Die 2 is " + dice.getValue2());
		/*	System.out.println("Die 1 is " + dice.getValue1());
			System.out.println("Die 2 is " + dice.getValue2());
		*/	
			player1.setTotalScore(dice.getSum());
			
			userInterface.outputMessage("Rolling dice for player two ");
		//	System.out.println("Rolling dice for player two ");
		
			dice.roll();
		
			userInterface.outputMessage("Die 1 is " + dice.getValue1());
			userInterface.outputMessage("Die 2 is " + dice.getValue2());
		/*	System.out.println("Die 1 is " + dice.getValue1());
			System.out.println("Die 2 is " + dice.getValue2());
		*/
			player2.setTotalScore(dice.getSum());
			
			userInterface.outputMessage(player1.toString());
			userInterface.outputMessage(player2.toString());
		/*	System.out.println(player1.toString());
			System.out.println(player2.toString());
		*/
			choice = "n";
			if ((player1.getTotalScore() >= SCORE_TO_WIN) && (player2.getTotalScore() >= SCORE_TO_WIN)) {
				userInterface.outputMessage("DRAW");
			//	System.out.println("DRAW");
			} else if (player1.getTotalScore() >= SCORE_TO_WIN) {
				userInterface.outputMessage(player1.getName() + " wins");
			//	System.out.println(player1.getName() + " wins");
			} else if (player2.getTotalScore() >= SCORE_TO_WIN) {
				userInterface.outputMessage(player2.getName() + " wins");
			//	System.out.println(player2.getName() + " wins");
			} else {
				userInterface.outputMessage("No winner yet");
			//	System.out.println("No winner yet");
				
				userInterface.outputMessage("Roll the dice? (y/n): ");
				choice = userInterface.getInput().toLowerCase();
				/*
				System.out.println("Roll the dice again? (y/n): ");
				choice = sc.next().toLowerCase();
				*/
			}
		}
		System.out.println("Good bye!");
	}

}
