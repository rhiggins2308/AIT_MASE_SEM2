package generics;
// http://www.stackoverflow.com/questions/8363230/java-find-multiple-modes

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// calculating the mode (most frequently occuring value or values) in an array 
// is an excellent example of a HashMap:

/* Algorithm:
Use a HashMap<Integer, Integer>. When you find a new number from the user, 
add the input as the key and 1 as the value. When you find a number from the 
user that's already contained in the HashMap, change the value for that 
key (the user's number) to the current value + 1.

Once you're done inputting, go through the elements of the HashMap to find 
the largest value. 

*/
public class CalculateMode {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Enter number of data items: ");
        int n=sc.nextInt();

        System.out.println("Enter the data");
        
        Map<Integer, Integer> numberMap = new HashMap<Integer, 
                Integer>();
        int highestFreq = populateTheMap(n, numberMap);
        
        for (Integer key: numberMap.keySet()){
            if (numberMap.get(key) == highestFreq)
                System.out.println(key + " occurs "+ 
                        highestFreq + " times.");
        }        
        
        
    }
    public static int populateTheMap(int numberOfDataItems, 
            Map<Integer, Integer> numberMap){
        int numToAdd = 0;
        int highestFreq = 0;
        // Count all the numbers
        for(int i=1; i<=numberOfDataItems; i++){
            numToAdd = sc.nextInt();
            if (numberMap.containsKey(numToAdd)){
                int currVal = numberMap.get(numToAdd);
                numberMap.put(numToAdd, currVal + 1);
            }
            else
                numberMap.put(numToAdd, 1);
            // Check if highest frequency
            if (numberMap.get(numToAdd) > highestFreq)
                highestFreq = numberMap.get(numToAdd);
        }
        return highestFreq;
    }
    
}
