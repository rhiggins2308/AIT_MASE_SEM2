package lectures.week9.concurrency.thread_coordination;

public class Producer implements Runnable {
	private MyStack theStack;
	
	public Producer(MyStack stack) {
		theStack = stack;
	}
	
	@Override
	public void run() {
		char c;
		for (int i = 1; i < 20; i++) {
			c = (char)(Math.random() * 26 + 'A');	// 'A' ... 'Z'
			theStack.push(c);;
			System.out.println("Producer: " + c);
			try {
				Thread.sleep((int)(Math.random() * 300));
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}