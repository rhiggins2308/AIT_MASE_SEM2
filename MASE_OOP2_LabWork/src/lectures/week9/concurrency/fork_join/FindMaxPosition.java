package lectures.week9.concurrency.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class FindMaxPosition extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 10_000;
	private int data[], low, high;
	
	public FindMaxPosition(int[] data, int low, int high) {
		this.data = data;
		this.low = low;
		this.high = high;
	}
	
	@Override
	protected Integer compute() {
		if (high - low <= THRESHOLD) {					// is the task manageable?
			int position = 0;							// YES: do the task
			for (int i = low; i < high; i++) {
				if (data[i] > data[position]) {
					position = i;
				}
			}
			return position;
		} else {
			int halfway = ((high - low) / 2) + low;		// NO: task too big, split it
			FindMaxPosition left = new FindMaxPosition(data, low, halfway);
			FindMaxPosition right = new FindMaxPosition(data, halfway, high);
			left.fork();					// queue the left half
			int maxPosRHS = right.compute();	// work on the right half (recursive call)
			int maxPosLHS = left.join();		// wait for the queued task to complete
			/* which of the two max positions returned is the greater?
			 * i.e. maxPosRHS represents the position of the max value in the right half
			 * 		and maxPosLHS represents the position of the max value in the left half
			 */
			if (data[maxPosLHS] > data[maxPosRHS]) {
				return maxPosLHS;
			} else if (data[maxPosRHS] > data[maxPosLHS]) {
				return maxPosRHS;
			} else {
				// equal, return first occurrence (checking indices here, 
				// not the values at thos indices)
				return maxPosLHS < maxPosRHS ? maxPosLHS : maxPosRHS;
			}

		}
	}
	public static void main(String[] args) {
		int[] data = new int [10_000_000];
		ForkJoinPool fjPool = new ForkJoinPool();
		RandomInit action = new RandomInit(data, 0, data.length);
		fjPool.invoke(action);	// calls the tasks compute() method. return type is void
		
		// new part (RecursiveTask)
		FindMaxPosition task = new FindMaxPosition(data, 0, data.length);
		Integer maxPosition = fjPool.invoke(task); // return value for RecursiveTask
		System.out.println("Position: " + maxPosition + "; value: " + data[maxPosition]);
	}
}