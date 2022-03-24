package labs.week9.concurrency;

import java.util.concurrent.ForkJoinPool;

public class Main {

    //We use a lot of memory
    //The project properties should have a 
    // Run, VM Option of -Xmx1024m
    public static void main(String[] args) {
        int[] data = new int[1024 * 1024 * 128]; //512MB

//        for (int i = 0; i < data.length; i++) {
//            data[i] = ThreadLocalRandom.current().nextInt();
//        }

//        int max = Integer.MIN_VALUE;
//        for (int value : data) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        System.out.println("Max value found:" + max);
        
        // Creates a ForkJoinPool 
        ForkJoinPool pool = new ForkJoinPool();
        
        // These are the extra lines *******
        // public class RandomArrayAction extends RecursiveAction  *whereas* 
        // public class FindMaxTask extends RecursiveTask<Integer>
        RandomArrayAction action = 
                new RandomArrayAction(data, 0, data.length-1, data.length/16);
        // invoke() - Performs the given task, returning its result upon completion.
        // Note: You cannot get a return value back from "invoke()" even though the API is 
        // invoke(ForkJoinTask<V>).
        // RecursiveAction is a subclass of ForkJoinTask, which is what invoke accepts. 
        // However, the API states that RecursiveAction is : "A recursive resultless ForkJoinTask. 
        // This class establishes conventions to parameterize resultless actions 
        // as **Void ForkJoinTasks**". 
        pool.invoke(action);    // taking a RecursiveAction (subclass of ForkJoinTask<V>)
        
        
        // unchanged - as in the practice...
        FindMaxTask task = new FindMaxTask(data, 0, data.length-1, data.length/16);
        // invoke() - Performs the given task, returning its result upon completion.
        Integer result = pool.invoke(task); // taking a RecursiveTask<Integer> (subclass of ForkJoinTask<V>)
        System.out.println("Max value found:" + result);
        
    }
}
