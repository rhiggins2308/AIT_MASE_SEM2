package com.ait.ui;

import java.util.Scanner;

public class Validator
{
    public static String getString(final Scanner scanner, final String prompt)
    {
        System.out.print(prompt);
        final String string = scanner.next();        // read the first string on the line
        scanner.nextLine();               // discard any other data entered on the line
        return string;
    }

    public static String getLine(final Scanner scanner, final String prompt)
    {
        System.out.print(prompt);
        final String string = scanner.nextLine();        // read the whole line
        return string;
    }

    public static int getInt(final Scanner scanner, final String prompt)
    {
        //boolean isValid = false;
       int selection = 0;
       // while (isValid)
       // {
            System.out.print(prompt);
            if (scanner.hasNextInt())
            {
                selection = scanner.nextInt();
                //isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            scanner.nextLine();  // discard any other data entered on the line
        //}
        return selection;
    }

    

   

    

}
