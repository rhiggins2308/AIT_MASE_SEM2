package lectures.week9.concurrency.executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class VariousTypes {

	public static void main(String[] args) {
		createVariousThreadTypes();
		runExample();
	}
	
	public static void createVariousThreadTypes () {
		// CachedThreadPool
				ExecutorService es = Executors.newCachedThreadPool();
				
				// FixedThreadPool
				int cpuCount = Runtime.getRuntime().availableProcessors();
				ExecutorService es2 = Executors.newFixedThreadPool(cpuCount);
				
				// SingleThreadExecutor
				ExecutorService es3 = Executors.newSingleThreadExecutor();
	}
	
	public static void runExample() {
		// create an ExecutorServcice with a fixed thread pool consisting of one thread
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		// execute the Runnable task (asynchronously) - void run()
		es.execute( () -> System.out.println("Runnable example"));
		
		// shut down the executor service otherwise this application will never terminate
		// existing tasks will be allowed to complete but no new tasks accepted
		es.shutdown();
	}
	
	public static void callableExample() throws java.util.concurrent.TimeoutException {
		// create an ExecutorService with a fixed thread pool consisting of one thread
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		// submit the Callable task (asynchronously) to the executor service
		// and store the Future object
		Future<Integer> future = es.submit(() -> 3 + 5); // V call()
		
		try {
			// get() will block for 500 msec max.
			// TimeUnit is an enum
			System.out.println("The answer is: " + future.get(500, TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			ex.printStackTrace();
		}
		
		// shutdown the executor service otherwise this application will never terminate
		// existing tasks will be allowed to complete but no new tasks accepted
		es.shutdown();
	}
}