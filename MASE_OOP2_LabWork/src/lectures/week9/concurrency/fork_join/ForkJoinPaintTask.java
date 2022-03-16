package lectures.week9.concurrency.fork_join;

public class ForkJoinPaintTask {
	/* 
	 * Pseudocode below
	 * using example of painting a wall 
	 * or dividing to smaller sections 
	 * if it is too large
	 */
	
	/*
	 * compute() {	// overriding
	 * 		if (isFenceSectionSmall()) {	// is it small enough?
	 * 			paintFenceSection();		// Yes: paint it
	 * 		} else {
	 * 			// No: divide and conquer
	 * 			ForkJoinPaintTask leftHalf = getLeftHalfOfFence();
	 * 			leftHalf.fork();			// queue/fork the left half
	 * 			
	 * 			ForkJoinPaintTask rightHalf = getRightHalfOfFence();
	 * 			rightHalf.fork();			// queue/fork the right half
	 * 			
	 * 			rightHalf.compute();		// work on the right half (recursive call)
	 * 			leftHalf.join();			// wait for the forked task to complete	 * 
	 * }
	 */

}
