package labs.week9.concurrency;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

// RecursiveAction is not generically typed because compute() returns void
// Class ForkJoinTask<V> is a parent of both RecursiveAction and RecursiveTask
public class RandomArrayAction extends RecursiveAction {
    private final int threshold;
    private final int[] myArray;
    private int start;
    private int end;

    public RandomArrayAction(int[] myArray, int start, int end, int threshold) {
        this.threshold = threshold;
        this.myArray = myArray;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < threshold) {
            for (int i = start; i <= end; i++) {
                myArray[i] = ThreadLocalRandom.current().nextInt();
            }
        } else {
            int midway = (end - start) / 2 + start;
            RandomArrayAction topHalf = 
                    new RandomArrayAction(myArray, start, midway, threshold);
            RandomArrayAction bottomHalf = 
                    new RandomArrayAction(myArray, midway + 1, end, threshold);
            // RecursiveAction "is-a" ForkJoinTask 
            // (it is a subclass)
            // Forks the given tasks, returning 
            // when they are completed...
            invokeAll(topHalf, bottomHalf);
        }
    }
}