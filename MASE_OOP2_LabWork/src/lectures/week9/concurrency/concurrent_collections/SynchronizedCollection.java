package lectures.week9.concurrency.concurrent_collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedCollection {

	public static void main(String[] args) {
//		Map<String, String> capitalCities = new HashMap<>();
		Map<String, String> capitalCities = new ConcurrentHashMap<>();

		capitalCities.put("Oslo", "Norway");
		capitalCities.put("Copenhagen", "Denmark");
		
		Map<String, String> syncCapitalCities = Collections.synchronizedMap(capitalCities);
		for (String key : syncCapitalCities.keySet()) {
			System.out.println(key + " is the capital of " + syncCapitalCities.get(key));
//			syncCapitalCities.remove(key);	// ConcurrentModificationException
		}
	}
}