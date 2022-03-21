package mase.oop2.code1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MASE_OOP2_CodeAssessment1_2022 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final int SORTING = 1, COLLECTIONS = 2, STREAMS = 3, EXIT = 4;
        String userContinue = "y";

        while (userContinue.equalsIgnoreCase("y")) {
            switch (userChoice()) {
                case SORTING:
                    sorting();
                    break;
                case COLLECTIONS:
                    collections();
                    break;
                case STREAMS:
                    streams();
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

    public static void streams(){
        // GIVEN - DO NOT CHANGE
        List<Student> students = Arrays.asList(
                                    new Student("A. Bloggs", "A011", 22, 2.0 ),
                                    new Student("B. Bloggs", "A012", 19, 1.9 ),
                                    new Student("E. Bloggs", "A010", 20, 2.1 ),
                                    new Student("D. Bloggs", "A009", 19, 1.8 ),
                                    new Student("C. Bloggs", "A013", 20, 1.7 )
        );
        
        // A 
        students.stream()
        	.sorted((s1, s2) -> s1.getId().compareTo(s2.getId()))
        	.forEach((s) -> System.out.println(s.getId() + " " + s.getName()));
        
        // B
        students.stream()
        	.collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getName, Collectors.toList())))
        	.forEach((key, value) -> { System.out.println(key + "->" + value);
        	});
        System.out.println();
       
        // C
        OptionalDouble avgHeight = students.stream()
        	.filter(s -> s.getHeight() >= 2.0)
        	.mapToDouble(s -> s.getHeight())
        	.average();        
        System.out.println("Average height (>= 2 metres): " + avgHeight.getAsDouble());
                
//        // C - Jacks version
//        double avgAboveTwoMetres = students.stream()
//        	.filter(student -> student.getHeight()>=2)
//        	.mapToDouble(Student::getHeight)
//        	.average().orElse(0);
//        System.out.println("Average height (>= 2 metres): "+avgAboveTwoMetres);
//        System.out.println();
//        
        
        // D
        students.stream()
        	.collect(Collectors.toMap(Student::getAge, Student::getHeight, (student1, student2) -> student1 + student2))
        	.forEach((age, height) -> {
	        	System.out.print(age + " " + height);
	        	System.out.println();
        	});
        System.out.println();
        
        // E
        List<String> studentNames = students.stream()
        	.map(Student::getName)
        	.collect(Collectors.toList());
        System.out.println(studentNames);
    }

    public static void collections() {
        // GIVEN - DO NOT CHANGE
        Map<String, Integer> makeToSales    = new TreeMap<>(); // make, sales
        Map<String, String> makeToCountry   = new TreeMap<>(); // make, country
        Map<String, Integer> salesByCountry = new TreeMap<>(); // country, sales

        // make -> sales
        // K -> V1
        // GIVEN - DO NOT CHANGE
        makeToSales.put("Ford", 20_000); 
        makeToSales.put("Chevrolet", 5_000); 
        makeToSales.put("VW", 30_000); 
        makeToSales.put("Mercedes", 10_000); 
        makeToSales.put("BMW", 9_000); 
        makeToSales.put("Nissan", 15_000); 
        
        // make -> country
        // K -> V2
        // GIVEN - DO NOT CHANGE
        makeToCountry.put("Ford", "USA");
        makeToCountry.put("Chevrolet", "USA");
        makeToCountry.put("VW", "Germany");
        makeToCountry.put("Mercedes", "Germany");
        makeToCountry.put("BMW", "Germany");
        makeToCountry.put("Nissan", "Japan");

        // Setup "salesByCountry"
        // country -> number of sales (total)   
        makeToSales.forEach((make, sales) ->{
            String country = makeToCountry.get(make);  // get the publisher for that channel
            if (salesByCountry.containsKey(country)) {
                int currentSales = salesByCountry.get(country);
                int newSales = currentSales + sales;
                salesByCountry.put(country, newSales);// replaces old value 
            } else {
                salesByCountry.put(country, sales);
            }            
        });

        
        // Output "salesByCountry"
        salesByCountry.forEach(
            (country, sales) -> 
                System.out.println("country: "+country+"; numSales:"+sales));
    
        // What country has the most/least sales?
        	int minSales = Collections.min(salesByCountry.values());
        	int maxSales = Collections.max(salesByCountry.values());
        
        	salesByCountry.forEach((country, sales) -> {
            if (sales == maxSales) {
                System.out.println("Country with most sales: " + country + " " + sales);
            } else if (sales == minSales) {
                System.out.println("Country with least sales: " + country + " " + sales);
            }
        });
    }

    public static void sorting(){
        // GIVEN - DO NOT CHANGE
        List<Student> students = Arrays.asList(
                                    new Student("A. Bloggs", "A011", 22, 2.0 ),
                                    new Student("B. Bloggs", "A012", 19, 1.9 ),
                                    new Student("E. Bloggs", "A010", 20, 2.1 ),
                                    new Student("D. Bloggs", "A009", 19, 1.8 ),
                                    new Student("C. Bloggs", "A013", 20, 1.7 )
        );

        System.out.println("Default sort (by id)");
        Collections.sort(students);
        students.forEach(System.out::print);
        
        System.out.println("Sort by name");
        students.sort(Comparator.comparing(Student::getName));
        students.forEach(System.out::print);
    }
    
    public static int userChoice() {
        System.out.println("\nWhat do you want to do ?");
        System.out.println("1. Sorting");
        System.out.println("2. Collections");
        System.out.println("3. Streams");
        System.out.println("4. Exit");
        System.out.print("Enter choice --> ");
        return sc.nextInt();
    }
}