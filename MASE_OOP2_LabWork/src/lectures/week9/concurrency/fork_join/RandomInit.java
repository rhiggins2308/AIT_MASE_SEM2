package lectures.week9.concurrency.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInit extends RecursiveAction{

	private static final int THRESHOLD = 10_000;
	private int data[], low, high;
	
	public RandomInit(int[] data, int low, int high) {
		this.data = data;
		this.low = low;
		this.high = high;
	}
	
	@Override
	protected void compute() {
		if (high - low <= THRESHOLD) {	// is the task manageable?
			// YES: do the task
			for (int i = low; i < high; i++) {
				data[i] = ThreadLocalRandom.current().nextInt();
			}
		} else {
			// NO: task too big, split it
			int halfway = ((high - low) / 2) + low;
			RandomInit left = new RandomInit(data, low, halfway);
			RandomInit right = new RandomInit(data, halfway, high);
			left.fork();		// queue the left half
			right.compute();	// work on the right half (recursive call)
			left.join();		// wait for the queued task to complete
			// invokeAll(right, left);
		}
	}
	
	public static void main(String[] args) {
		int[] data = new int [10_000_000];
		ForkJoinPool fjPool = new ForkJoinPool();
		RandomInit action = new RandomInit(data, 0, data.length);
		fjPool.invoke(action);	// calls the tasks compute() method

	}

}
