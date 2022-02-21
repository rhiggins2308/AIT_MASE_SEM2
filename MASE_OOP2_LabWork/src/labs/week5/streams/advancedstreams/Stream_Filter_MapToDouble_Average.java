package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.List;

public class Stream_Filter_MapToDouble_Average {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(new Book("Thinking in Java", 30.0),  
                                         new Book("Java in 24 hrs", 20.0),   
                                         new Book("Java Recipies", 10.0)); 
        double averagePrice = books.stream()
                .filter(book->book.getPrice()>10)    
                // DoubleStream mapToDouble(ToDoubleFunction)
                //   ToDoubleFunction is a functional interface:
                //      double applyAsDouble(T value)
                .mapToDouble(book->book.getPrice())     
                // OptionalDouble average()  - terminal operation
                .average()
//                .getAsDouble(); 
                .orElse(0.0); // useful if filter filters out ALL of the Books
        System.out.println(averagePrice);       
    }
    
}

class Book{     
    private String title;     
    private double price;     
    Book(String title, double price){         
        this.title = title;         
        this.price = price;     
    }     
    public String getTitle() {   return title;}
    public double getPrice() {   return price;}
    public String toString() { return title+ " " + price;}
}