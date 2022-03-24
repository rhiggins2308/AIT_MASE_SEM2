// Name: Robert Higgins A00008705
package mase.oop2.code2;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MASE_OOP2_CodeAssessment2_2022 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        final int LOCALISATION=1, DATES_TIMES = 2, STREAMS = 3, EXIT = 4;  
        String userContinue = "y";

        while (userContinue.equalsIgnoreCase("y")) {
            switch (userChoice()) {
                case DATES_TIMES:
                    datesAndTimes();
                    break;
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
    
    public static void localisation() {
    	PrintWriter pw = new PrintWriter(System.out, true);
    	
    	Locale localeIE = new Locale("en", "IE");
    	Locale localeFI = new Locale("fi", "FI");
    	Locale localeNL = new Locale("nl", "NL");
    	
    	ResourceBundle seasons;
    	
    	System.out.println("Output the seasons in which language?\n"
        		+ "(D)utch, (F)innish or English (any other key)");
        
        String choice = sc.next();
        
        switch (choice) {
        	case "D":
        	case "d":
        		seasons = ResourceBundle.getBundle("SeasonsBundle", localeNL);
        		break;
        	case "F":
        	case "f":
        		seasons = ResourceBundle.getBundle("SeasonsBundle", localeFI);
        		break;
        	default:
        		seasons = ResourceBundle.getBundle("SeasonsBundle", localeIE);
        		break;
        }
        
        pw.println("spring is " + seasons.getString("spring"));
    	pw.println("summer is " + seasons.getString("summer"));
    	pw.println("autumn is " + seasons.getString("autumn"));
    	pw.println("winter is " + seasons.getString("winter"));
    }
    
    public static void datesAndTimes(){
    	LocalDate startACW = LocalDate.of(1861, Month.APRIL, 12);
    	LocalDate endACW = LocalDate.of(1865, Month.APRIL, 9);
    	Period period = Period.between(startACW, endACW);
    	
    	System.out.println("Years: " + period.getYears());
    	System.out.println("Months: " + period.getMonths());
    	System.out.println("Days: " + period.getDays());
    	
    	LocalDateTime ldt = LocalDateTime.of(2018, 3, 17, 22, 10);
    	System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy/MMM/dd 'at' HH:mm")));
    }
    
    public static void streams() {
        // This code is given - DO NOT CHANGE.
        List<Book> books = Arrays.asList(
                new Book("Thinking in Java", "Computer Science", 50.0),
                new Book("Thinking in C++", "Computer Science", 45.0),
                new Book("Head First Java", "Computer Science", 55.0),
                new Book("Basic Cooking", "House and Home", 50.0),
                new Book("Playing the drums", "Music", 30.0),
                new Book("Learn the Guitar", "Music", 30.0));
        
        // A
        double minPrice = books.stream()
        	.filter(b -> b.getCategory().equals("Computer Science"))
        	.mapToDouble(Book::getPrice)
        	.min()
        	.getAsDouble();
       
        books.stream()
        	.filter(b -> b.getPrice() == minPrice)
        	.forEach(b -> {
           		System.out.println(b.toString());
        	});
               
        // B
        books.stream()
        	.filter(b -> b.getCategory().equals("Music"))
        	.forEach((b) -> {
        		System.out.println(b.getTitle() + ", " + b.getPrice());
        	});
        
        // C       
        double avgPrice = books.stream()
        	.mapToDouble(b -> b.getPrice())
        	.average().getAsDouble();
        System.out.println(avgPrice);
                
        // D
        books.stream()
        	.map(Book::getCategory)
        	.collect(Collectors.toSet())
        	.forEach(System.out::println);
    }

    public static int userChoice() {
        System.out.println("\nWhat do you want to do ?");
        System.out.println("1. Localisation");  
        System.out.println("2. Dates/Times"); 
        System.out.println("3. Streams"); 
        System.out.println("4. Exit");
        System.out.print("Enter choice --> ");
        return sc.nextInt();
    }
}