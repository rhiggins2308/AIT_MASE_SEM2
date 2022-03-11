package lectures.week9.concurrency.thread_coordination;

/*
 	An example that demonstrates that while synchronisation prevents corruption
 	of shared data, there is still a need for coordination of the threads.
 	Coordination is achieved with wait()/notify()/notifyAll().
 */

public class MyStack {
	private int top = 0;						// all data is private
	private int size = 100;
	private char[] buffer = new char[size];
	
	public synchronized void push(char c) {
		while (top == size) {
			// avoid overflow
			try {
				wait(); // release the lock and go into wait pool (for 'this')
			} catch (InterruptedException ie) {
				System.out.println("push() interrupted!");
			}
		}
		buffer[top] = c;
		top++;
		/* wake up all the threads in the wait pool waiting on 'this'
		 * move them to the lock pool, where they block until this thread
		 * releases the lock on 'this' (by hitting the '}' at the end of
		 * the method)
		 */
		notifyAll();
	} // release the lock

	public synchronized char pop() {
		while (top == 0) {
			// avoid underflow
			try {
				wait(); // release the lock and go into wait pool (for 'this')
			} catch (InterruptedException ie) {
				System.out.println("pop() interrupted!");
			}
		}
		top--;
		notifyAll();
		return buffer[top];	// release the lock
	}
												/*
												 * If a thread is executing push() or pop() then
												 * no other thread can execute push() or pop()
												 * - provided it is the same MyStack instance.
												 */	
	
	public void foo() {	// not synchronized, so can be executed freely
		// do something...
	}

}
