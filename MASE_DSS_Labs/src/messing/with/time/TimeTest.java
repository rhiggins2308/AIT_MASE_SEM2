package messing.with.time;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TimeTest {

	public static void main(String[] args) throws InterruptedException {
		
		LocalTime startTime = java.time.LocalTime.now();
		System.out.println("Query Start Time: " + startTime);
		TimeUnit.SECONDS.sleep(2);
		LocalTime endTime = java.time.LocalTime.now();
		System.out.println("Query End Time: " + endTime);
		
		

	}
}