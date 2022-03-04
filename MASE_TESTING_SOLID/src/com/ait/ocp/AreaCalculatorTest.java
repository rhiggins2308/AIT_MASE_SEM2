package com.ait.ocp;
import java.util.ArrayList;
public class AreaCalculatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rectangle firstRectangle=new Rectangle(5,4);
		Rectangle secondRectangle=new Rectangle(4,4);
		ArrayList<Rectangle> shapes=new ArrayList<Rectangle>();
		shapes.add(firstRectangle);
		shapes.add(secondRectangle);
		AreaCalculator areaCalculator=new AreaCalculator();
		System.out.println(areaCalculator.area(shapes));
	}

}
