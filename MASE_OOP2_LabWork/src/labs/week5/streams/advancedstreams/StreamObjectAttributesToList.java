package labs.week5.streams.advancedstreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* Given the AnotherBook class,  declare a List typed for AnotherBook namely ‘books’  with the following AnotherBook’s:
 * 		a.	title=”Gone with the wind”, genre=”Fiction”
 * 		b.	title=”Bourne Ultimatum”, genre=”Thriller”
 * 		c.	title=”The Client”, genre=”Thriller”
 * 
 * Declare the following:
 * 		List<String> genreList = new ArrayList<>(); 
 * 
 * Stream books so that genreList refers to a List containing the genres of the books in the books List.
 * 
 * (QID 2.1858)
 */
public class StreamObjectAttributesToList {
    public static void main(String[] args) {
        List<AnotherBook> books = Arrays.asList(  
                new AnotherBook("Gone with the wind", "Fiction"),        
                new AnotherBook("Bourne Ultimatum", "Thriller"),         
                new AnotherBook("The Client", "Thriller") );  
        
        List<String> genreList = new ArrayList<>(); 
 
// OPTION 1
        /* Stream the list of books
         * Then map each book to its genre using the method reference
         * Then forEach genre, add the genre to genreList
         * Resulting List will contain all genres from the original books List
         * NOTE: a List allows duplicate values, so two instances of Thriller are okay
         */
/*        books.stream()
        .map(AnotherBook::getGenre)
        .forEach(s -> genreList.add(s));
*/

// OPTION 2
        /* Stream the list of books
         * Then map each book to its genre using the method reference
         * Then collect, using Collectors.toList, to return a List of objects of the same type as passed in
         * i.e. a List of Strings corresponding to the genres of each book
         * Resulting List will contain all genres from the original books List
         * NOTE: a List allows duplicate values, so two instances of Thriller are okay
         */
        genreList = books.stream()
        		.map(AnotherBook::getGenre)
        		.collect(Collectors.toList());


// OPTION 3
        /* Stream the list of books
         * Then map each book to its genre using the method reference
         * Then collect, using Collectors.toList, to return a List of objects of the same type as passed in
         * INCORRECT
         * 		Collectors.toList does not take any arguments
         */
        //books.stream().map(AnotherBook::getGenre).collect(Collectors.toList(genreList));

        
// OPTION 4
        /* Stream the list of books
         * Then map each book to its genre using the method reference
         * Then forEach genre, add the genre to genreList
         * 		Same as OPTION 1, except forEach uses method reference
         * Resulting List will contain all genres from the original books List
         * NOTE: a List allows duplicate values, so two instances of Thriller are okay
         */
        books.stream().map(AnotherBook::getGenre).forEach(genreList::add);

        
// OPTION 5
        /* Stream the list of books
         * Then map each book to its genre using the lambda definition
         * 		Same as OPTION 4, except map uses lambda
         * Then forEach genre, add the genre to genreList method reference call
         * Resulting List will contain all genres from the original books List
         * NOTE: a List allows duplicate values, so two instances of Thriller are okay
         */
        //books.stream().map(b -> b.getGenre()).forEach(genreList::add);

        
// OPTION 6
        /* INCORRECT
         * flatMap is used when each element of a given Stream can itself generate a Stream of objects.
         * The purpose of flatMap is to extract the elements of each of those individual STreams
         * and return a single Stream, containing all of the elements
         */
        //books.stream().flatMap(b -> b.getGenre()).forEach(g -> genreList.add(g));

        
//        books.stream()
//            .map(book -> book.getGenre())     // lambda
//            .map(AnotherBook::getGenre)       // method reference
//                .map(                           // anonymous inner class
//                    new Function(){
//                        @Override
//                        public String apply(Object o){
//                            return ((AnotherBook)o).getGenre();
//                        }
//                    } 
//                )
//            .forEach(s->genreList.add(s));    // lambda
//           .forEach(genreList::add);         // method reference (bound)
//            .forEach(ArrayList::add);         // does not compile!
//                .forEach (                      // anonymous inner class
//                    new Consumer(){
//                        @Override
//                        public void accept(Object o){
//                            genreList.add((String)o);
//                        }
//                    }
//                );
        System.out.println(genreList);
    }
}

class AnotherBook {    
    private String title;
    private String genre;    
    public AnotherBook(String title, String genre){         
        this.title = title;
        this.genre = genre;    
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }  
}