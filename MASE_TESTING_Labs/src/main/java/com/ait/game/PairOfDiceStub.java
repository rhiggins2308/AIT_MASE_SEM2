package com.ait.game;

public class PairOfDiceStub {
	
	int counter=0;
	int value1;
	int value2;
	
	public PairOfDiceStub() {

	}
	
	public void roll()
	{
	
		// Player 1 Win
			
		if (counter==0) { //player1 roll1 ... total 12
			value1=6;
			value2=6;
		}
		else if(counter==1) { //player2 roll1 ... total 2
			value1=1;
			value2=1;
		}
		else if(counter==2) { //player1 roll2 ... total 24
			value1=6;
			value2=6;
		}
		else if(counter==3) { //player2 roll2 ... total 10
			value1=4;
			value2=4;
		}
		counter++;
		
	
	
	/*
		// Player 2 Win
		
				if (counter==0) { //player1 roll1 ... total 2
					value1=1;
					value2=1;
				}
				else if(counter==1) { //player2 roll1 ... total 12
					value1=6;
					value2=6;
				}
				else if(counter==2) { //player1 roll2 ... total 14
					value1=6;
					value2=6;
				}
				else if(counter==3) { //player2 roll2 ... total 24
					value1=6;
					value2=6;
				}
				counter++;
	*/
		
/*
		// Draw Game
		
				if (counter==0) { //player1 roll1 ... total 12
					value1=6;
					value2=6;
				}
				else if(counter==1) { //player2 roll1 ... total 12
					value1=6;
					value2=6;
				}
				else if(counter==2) { //player1 roll2 ... total 24
					value1=6;
					value2=6;
				}
				else if(counter==3) { //player2 roll2 ... total 24
					value1=6;
					value2=6;
				}
				counter++;
		*/
	}

	// get value of die1
	public int getValue1()
	{
		return value1;
	}

	// get value of die2
	public int getValue2()
	{
		return value2;
	}

	public int getSum()
	{
		return value1+value2;
	}
}
