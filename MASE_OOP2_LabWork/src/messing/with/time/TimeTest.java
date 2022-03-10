package messing.with.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeTest {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Query Start Time: " + getTime());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Query End Time: " + getTime());

	}

	private static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");  
	    Date date = new Date();
	    return date.toString();
	}
}