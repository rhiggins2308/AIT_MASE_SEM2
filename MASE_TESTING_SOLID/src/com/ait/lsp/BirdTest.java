package com.ait.lsp;

import java.util.ArrayList;

public class BirdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Bird> birdList = new ArrayList<Bird>();
		birdList.add(new Bird());
		birdList.add(new Crow());
		birdList.add(new Ostrich());
		letTheBirdsFly(birdList);
	}

	public static void letTheBirdsFly(ArrayList<Bird> birdList) {
		for (Bird b : birdList) {
			b.fly();
		}
	}
}
