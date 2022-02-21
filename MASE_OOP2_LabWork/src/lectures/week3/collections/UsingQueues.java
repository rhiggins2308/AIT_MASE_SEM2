package lectures.week3.collections;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class UsingQueues {

	public static void main(String[] args) {
		
		linkedListQueue();
		arrayDeque();
		priorityQueueNaturalOrdering();
		priorityQueueCustomOrdering();
	}

	private static void linkedListQueue() {
		// A FIFO queue (First In First Out)
		Queue<Integer> queue = new LinkedList<>();
		// add() inserts into queue (throws exception if no space exists - if capacity restricted)
		// offer() inserts into queue (returns false if no space exists - capacity restricted)
		queue.add(1); 	// Head -> [1]
		queue.offer(2); //	Head -> [1, 2]
		queue.add(3); 	//	Head -> [1, 2, 3]
		queue.offer(4); //	Head -> [1, 2, 3, 4]
		
		// element() retrieves but does not remove the head of the queue (throws exception if queue empty)
		// peek() retrieves but does not remove the head of the queue (returns null if queue empty)
		System.out.println(queue.element()); 	// 1
		System.out.println(queue.peek()); 		// 1
		System.out.println(queue); 				// [1, 2, 3, 4]
		
		// remove() - Retrieves and removes the head of this queue (throws exception if queue empty)
		// poll() - Retrieves and removes the head of this queue (retuns null if queue empty)
		System.out.println(queue.remove());		// 1	Head -> [2, 3, 4]
		System.out.println(queue.poll());		// 2	Head -> [3, 4]
		System.out.println(queue);				// [3, 4]
		
		// offer() / poll() and peek() are the preferred methods as they do not throw exceptions (P.O.P)
	}
	
	private static void arrayDeque() {
		// Deque = "doubly ended queue". Supports element insertion/removal at both ends.
		// ArrayDeque - resizeable-array implementation of the Deque interface (no capacity restrictions)
		Deque<Integer> numbers = new ArrayDeque<>();
		// "arg" (as in main(String[] args)
			// Deque methods that begin with "a", "r", or "g"
			//		addFirst(), addLast(),
			//		removeFirst(), removeLast(),
			//		getFirst(), getLast()
		// all throw exceptions if the deque is both capacity-constrained and full.
		
		// The other methods (POP):
			//	peekFirst(), peekLast(),
			//	offerFirst(), offerLast(),
			//	pollFirst(), pollLast()
		// rather than throw an exception, return null/false in the same situation
		
		// add at front (the head)
		numbers.add(1);				// Head -> [1] <- Tail
		numbers.addFirst(2);		// Head -> [2, 1] <- Tail		Exception thrown if deque is full
		numbers.offerFirst(3);		// Head -> [3, 2, 1] <- Tail	null/false returned if deque is full
		System.out.println("Head: " + numbers.getFirst() + ". Head: " + numbers.peekFirst()); 	// Head: 3. Head: 3
		
		// add at end (the tail)
		numbers.addLast(4);			// Head -> [3, 2, 1, 4] <- Tail
		numbers.offerLast(5);		// Head -> [3, 2, 1, 4, 5] <- Tail
		
		// remove from both ends
		numbers.removeFirst();		// Head -> [2, 1, 4, 5] <- Tail
		numbers.pollFirst();		// Head -> [1, 4, 5] <- Tail
		numbers.removeLast();		// Head -> [1, 4] <- Tail
		numbers.pollLast();			// Head -> [1] <- Tail
		System.out.println(numbers);	// [1]		
		
		// The common peek() / offer() / poll() in use:
		System.out.println(numbers.offer(11));	// true		Head -> [1, 11] <- Tail
		System.out.println(numbers.offer(12));	// true		Head -> [1, 11, 12] <- Tail
		System.out.println(numbers.peek());		// 1		Head -> [1, 11, 12] <- Tail
		System.out.println(numbers.poll());		// 1		Head -> [11, 12] <- Tail
		System.out.println(numbers.poll());		// 11		Head -> [12] <- Tail
		System.out.println(numbers.poll());		// 12		Head -> [] <- Tail
		System.out.println(numbers.poll());		// null		Head -> [] <- Tail
		}
	
	private static void priorityQueueNaturalOrdering() {
		// Natural Ordering
		Queue<String> names = new PriorityQueue<>(); // alphabetic ordering
		names.add("V");
		names.add("A");
		names.add("P");
		Iterator itNames = names.iterator();
		
		while (itNames.hasNext()) {
			System.out.println(names.poll() + " ");		// A P V
		}
		
		Queue<Integer> numbers = new PriorityQueue<>();	// numeric ordering
		numbers.add(11);
		numbers.add(2);
		numbers.add(5);
		
		Iterator itNumbers = numbers.iterator();
		
		while (itNumbers.hasNext()) {
			System.out.println(numbers.poll() + " ");	// 2 5 11
		}
	}
	
	private static void priorityQueueCustomOrdering() {
		// Ordering specified by a comparator at construction time
		
		// 1. Order by title of the book
		// Comparator.comparing(Function)
		//		API: "Accepots a function that extracts a Comparable sort key from a type T,
		//			and retuirns a Comparator<T> that compares by that sort key."
		//		Function<T, R>
		//			R apply(T t)
		Comparator<Book> comparatorTitle = Comparator.comparing(book -> book.getTitle());
//		Comparator<Book> comparatorTitle = Comparator.comparing(Book::getTitle);
		Queue<Book> booksByTitle = new PriorityQueue<>(comparatorTitle);	// order by title
		booksByTitle.add(new Book("Java", 55.0));
		booksByTitle.add(new Book("Python", 23.0));
		booksByTitle.add(new Book("C++", 99.0));
		
		System.out.println("Ordering by title:");
		Iterator itBooks = booksByTitle.iterator();
		
		while(itBooks.hasNext()) {
			Book book = booksByTitle.poll();
			System.out.println(book);
			/* 	C++		99.0
			 	Java	55.0
			 	Python	23.0 	*/
		}	
		
		// 2. Order by the price of the book
		Comparator<Book> comparatorPrice = Comparator.comparing(Book::getPrice);
		Queue<Book> booksByPrice = new PriorityQueue<>(comparatorPrice);	// order by price
		booksByPrice.add(new Book("Java", 55.0));
		booksByPrice.add(new Book("Python", 23.0));
		booksByPrice.add(new Book("C++", 99.0));
		
		System.out.println("Ordering by price:");
		Iterator itMoreBooks = booksByPrice.iterator();
		
		while(itMoreBooks.hasNext()) {
			Book book = booksByPrice.poll();
			System.out.println(book);
			/* 	C++		99.0
			 	Java	55.0
			 	Python	23.0 	*/
		}	
	}
}

class Book {
	private String title;	// implements Comparable
	private Double price;	// implements Comparable
	
	public Book(String title, Double price) {
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "\t" + title + "\t" + price;
	}
}