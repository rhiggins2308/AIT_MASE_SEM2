package lectures.week9.concurrency.thread_coordination;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RailwayStation {	// this class simulated arrival of trains in a railway station
	private static Lock station = new ReentrantLock();
	private static Condition joeArrival = station.newCondition();

	// First, create a thread that waits for Joe to arrive and then create new Train threads
	public static void main(String[] args) {
		// Our wait in the railway station for Joe is simulated by this thread.
		// Once we get notification that Joe's train has arrived, we pick him up.
		new Thread(() -> {
			System.out.println("* Waiting in the station for IC1122 in which Joe is coming");
			station.lock();
			try {
				// await Joe's train arrival (and release the 'station' lock)
				//		Before calling await(), you must have locked the Lock used to produce the Condition
				joeArrival.await();
				// if this statement executes, it means we got a train arrival signal
				System.out.println("* Pick-up Joe!");
			} catch (InterruptedException ie ) { ie.printStackTrace(); }
			finally {
				station.unlock();
			}
		}).start();
		
		Runnable trainRunnable = () -> {
			station.lock();
			try {
				System.out.println(Thread.currentThread().getName() + ": I've arrived in the station ");
				if (Thread.currentThread().getName().startsWith("IC1122")) {
					// Joe's train has arrived; wake up the waiting thread
					joeArrival.signalAll();
				}
			} finally {
				station.unlock();
			}
		};
		
		// Trains are separate threads = they can arrive in any order
		Thread parisToMunich = new Thread(trainRunnable);
		parisToMunich.setName("IC1234 - Paris to Munich");
		parisToMunich.start();

		Thread parisToMadrid = new Thread(trainRunnable);
		parisToMadrid.setName("IC2211 - Paris to Madrid");
		parisToMadrid.start();
		Thread madridToParis = new Thread(trainRunnable);
		madridToParis.setName("IC1122 - Madrid to Paris");
		madridToParis.start();
		Thread munichToParis = new Thread(trainRunnable);
		munichToParis.setName("IC4321 - Munich to Paris");
		munichToParis.start();
	}
}