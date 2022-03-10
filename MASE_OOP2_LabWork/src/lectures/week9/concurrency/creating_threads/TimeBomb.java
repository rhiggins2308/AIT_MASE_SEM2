package lectures.week9.concurrency.creating_threads;

class CountDown implements Runnable {
	String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	
	@Override
	public void run() {
		for (int i = 9; i >= 0; i--) {
			try {
				System.out.println(timeStr[i]);
				//Thread.sleep(1000);
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

public class TimeBomb {

	public static void main(String[] args) {
		Thread timer = new Thread(new CountDown());
		System.out.println("Starting 10 second count down ... ");
		timer.start();
		try {
			timer.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("Boom!!!");
	}
}