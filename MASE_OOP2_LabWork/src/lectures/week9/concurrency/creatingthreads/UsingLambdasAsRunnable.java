package lectures.week9.concurrency.creatingthreads;

public class UsingLambdasAsRunnable {

	public static void main(String[] args) {
		Thread t = new Thread( () -> System.out.println("run(): " + Thread.currentThread().getName()));
		t.start();
		
		System.out.println("main(): " + Thread.currentThread().getName());
	}
}