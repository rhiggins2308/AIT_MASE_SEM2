package lectures.week9.concurrency.creatingthreads;

// sleep() and join()
class CountDown implements Runnable {
	String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	
	@Override
	public void run() {
		for (int i = 9; i >= 0; i--) {
			System.out.println(timeStr[i]);
		}
	}
}
public class TimeBomb {

	public static void main(String[] args) {
		Thread timer = new Thread(new CountDown());
		System.out.println("Starting 10 second count down ... ");
		timer.start();
		System.out.println("Boom!");
	}
}