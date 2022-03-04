package com.ait.game2;

public class DiceRollerApp
{
	
    public static void main(String args[])
    {
        Game game=new Game(UserInterfaceFactory.getInstance(), DiceFactory.getInstance());
    	game.start();
	}

} 