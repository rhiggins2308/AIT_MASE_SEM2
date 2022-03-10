package lectures.week9.concurrency.thread_safety;
/*
 * 1.
 * 		public synchronized static void addToCounter() {
 * 
 * 2.
 * 		public static void addToCounter() {
 * 			synchronized(FixRaceWithSynchronized.class) {
 * 
 * 3.
 * 		synchronized(lock) {
 * 
 * 4.
 * 		synchronize on 'this'
 * 			- make addToCounter() an instance method
 * 			- synchronized(this) inside the method
 * 			- create an instance of the overall class FixRaceWithSynchronized in main()
 * 			- ensure that all the threads share the same instance!
 */
public class FixRaceWithSynchronized {
//	private static Object lock = new Object();
	private static int count = 0;
	
	public synchronized static void addToCounter() {
		// public static void addToCounter() {
		//		synchronized(FixRaceWithSynchronized.class) {
		//		synchronized(lock) {
		int c = count;
		System.out.println("Before: " + count + ". Thread id: " + Thread.currentThread().getId());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
