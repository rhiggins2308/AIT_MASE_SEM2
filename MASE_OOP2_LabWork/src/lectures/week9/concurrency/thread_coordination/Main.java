package lectures.week9.concurrency.thread_coordination;

public class Main {

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		
		new Thread(new Producer(stack)).start();
		new Thread(new Consumer(stack)).start();
	}
}