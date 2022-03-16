package lectures.week9.concurrency.thread_coordination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MaxValueCollection {
	/* This is an example of how to make a non-thread safe collection
	 * (ArrayList) thread-safe, allowing concurrent reads but
	 * exclusive access for writing. Note that, instead of this model
	 * you can use one of the thread-safe collections ...
	 * 	e.g. List list = Collections.synchronizedList(new ArrayList());	 * 
	 */
	private List<Integer> integers = new ArrayList<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void add(Integer i) {
		rwl.writeLock().lock(); 	// one at a time
		try {
			integers.add(i);
		} finally {
			rwl.writeLock().unlock();
		}
	}
	
	public int findMax() {
		rwl.readLock().lock(); 		// many at once
		try {
			return Collections.max(integers);
		} finally {
			rwl.readLock().unlock();
		}
	}
}
