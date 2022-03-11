package lectures.week9.concurrency.thread_coordination;

public class Consumer implements Runnable {

	private MyStack theStack;
	
	public Consumer(MyStack stack) {
		theStack = stack;
	}
	
	@Override
	public void run() {
		char c;
		for (int i = 1; i < 20; i++) {
			c = theStack.pop();
			System.out.println("Consumer: " + c);
			try {
				Thread.sleep((int)(Math.random() * 300));
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}