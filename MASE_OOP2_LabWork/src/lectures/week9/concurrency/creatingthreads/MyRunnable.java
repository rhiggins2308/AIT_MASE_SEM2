package lectures.week9.concurrency.creatingthreads;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("run(): " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		new Thread(new MyRunnable()).start();
		System.out.println("main(): " + Thread.currentThread().getName());
	}
}