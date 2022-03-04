package com.ait.game2;
public class Die
{
    private int value;
    private static int SIDES=6;

    public Die()
    {
       this.value = 0;  // initialize value to 0
    }


    // roll the die
    public void roll()
    {
        value = (int) Math.floor((Math.random() * SIDES)+1); // number from 0 to sides
    }

    public int getValue()
    {
        return this.value;
    }
}