package lectures.week9.concurrency.thread_safety;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FixRaceWithLock {

	private static int count = 0;
	private static Lock lock = new ReentrantLock();
	
	public static void addToCounter() {
		if (lock.tryLock()) {	// use if/else WITHOUT lock.lock(); statement in try block
			try {
//				lock.lock(); // blocking callint c = count;
				int c = count;
				System.out.println("Before. " + count + ". Thread id: " + Thread.currentThread().getId());
				count = c + 1;
				System.out.println("After. " + count + ". Thread id: " + Thread.currentThread().getId());	
			} finally {
				lock.unlock();
			}
		} else {
			// did not get the lock, do something else
			System.out.println("Failed to get lock ...");
		}
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			new Thread(() -> addToCounter())
					.start();
		}
	}
}