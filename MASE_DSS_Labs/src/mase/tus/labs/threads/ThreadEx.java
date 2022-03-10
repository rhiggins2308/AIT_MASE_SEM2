package mase.tus.labs.threads;

public class ThreadEx {

	public static void main(String[] args) {
		
		Thread t1 = new MyThread(100) ;
		Thread t2 = new MyThread(200) ;
		t1.start();
		t2.start();

	}
}

class MyThread extends Thread {
	int max ;

	public MyThread(int max) {
		this.max = max;
	}

	public void run() {
		for(int i=0; i<max; i++) {
			System.out.println(max + " : " + i);
		}
	}
}
