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
        
        /* A
         * stream the students; sort them by their natural order i.e. 'id' and
         * output only the students id and name, separated by a space.
         * Do not modify the toString() in Student.
         * 	See sample output
         * 
         * A009 D. Bloggs
         * A010 E. Bloggs
         * A011 A. Bloggs
         * A012 B. Bloggs
         * A013 C. Bloggs         * 
         */
        students.stream()
        	.sorted((s1, s2) -> s1.getId().compareTo(s2.getId()))
        	.forEach((s) -> System.out.println(s.getId() + " " + s.getName()));
        
        
        
        /* B
         * stream the students; group the students by their age to a List of their names;
         * in the output, separate the age from the list of names by an arrow, i.e. "->"
         * 	See sample output
         * 
         * 19-> [B. Bloggs, D. Bloggs]
         * 20-> [E. Bloggs, C. Bloggs]
         * 22-> [A. Bloggs]
         * 
         */
        students.stream()
        	.collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getName, Collectors.toList())))
        	.forEach((key, value) -> { System.out.println(key + "->" + value);
        	});
        System.out.println();
       
        
        /* C
         * stream the students; calculate (using the average() method from DoubleStream)
         * the average height of students >= 2.0 (metres) tall.
         * The return type of your pipeline should be double.
         * 	See sample output
         * 
         * Average Height (>= 2 metres): 20.5
         */        
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

        
        /* D
         * stream the students; collect into a Map
         * where the key is the student age and the value is the student height.
         * If you get duplicate keys, add the values.
         * 	See sample output
         * 
         * 19 3.7
         * 20 3.8
         * 22 2.0
         */
        students.stream()
        	.collect(Collectors.toMap(Student::getAge, Student::getHeight, (student1, student2) -> student1 + student2))
        	.forEach((age, height) -> {
	        	System.out.print(age + " " + height);
	        	System.out.println();
        	});
        System.out.println();
        
        /* E
         * stream the students; collect into a List where the list only contains the student names.
         * See sample output.
         * 
         * [A. Bloggs, B. Bloggs, E. Bloggs, D. Bloggs, C. Bloggs]
         */
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

        
        /*
         * 2a Given teh 'makeToSales' and 'makeToCountry' maps;
         * 		populate the 'salesByCoiuntry' (country -> sales) map.
         */
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

        /*
         * 2b. Output the details of 'salesByCountry' using forEach(BiConsumer).
         * 		See sample output
         * 
         * country: Germany; numSales:49000
         * country: Japan; numSales:15000
         * country: USA; numSales:25000
         */
        // Output "salesByCountry"
        salesByCountry.forEach(
            (country, sales) -> 
                System.out.println("country: "+country+"; numSales:"+sales));
    
        /*
         * 2c. Calculate and output the country with the least and most sales.
         * 		See sample output
         * 
         * Country with the most sales: Germany 49000
         * Country with the least sales: Japan 15000
         */
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
        
        /*
         * 1b. Using the Collections class's sort(List) method, sort the list.
         */
        Collections.sort(students);
        
        /*
         * 1c. Output the list using List's forEach(Consumer). See example output
         * 
         * Default sort (by id)
         * Student{name=D. Bloggs, id=A009, age=19, height=1.8}
         * Student{name=E. Bloggs, id=A010, age=20, height=2.1}
         * Student{name=A. Bloggs, id=A011, age=22, height=2.0}
         * Student{name=B. Bloggs, id=A012, age=19, height=1.9}
         * Student{name=C. Bloggs, id=A013, age=20, height=1.7}
         * 
         */
        students.forEach(System.out::print);
        
        System.out.println("Sort by name");
        
        /*
         * 1d. Using the Collections class's sort(List, Comparator) method, sort the list.
         */
        students.sort(Comparator.comparing(Student::getName));
        
        /*
         * 1e. Output the list using List's forEach(Consumer). See example output
         * 
         * Sort by name
         * Student{name=A. Bloggs, id=A011, age=22, height=2.0}
         * Student{name=B. Bloggs, id=A012, age=19, height=1.9}
         * Student{name=C. Bloggs, id=A013, age=20, height=1.7}
         * Student{name=D. Bloggs, id=A009, age=19, height=1.8}
         * Student{name=E. Bloggs, id=A010, age=20, height=2.1}
         * 
         */
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