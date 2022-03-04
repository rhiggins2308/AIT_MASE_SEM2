package com.ait.ocpa;
import java.util.ArrayList;
public class AreaCalculatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rectangle firstRectangle=new Rectangle(5,4);
		Circle firstCircle=new Circle(2);
		ArrayList shapes=new ArrayList();
		shapes.add(firstRectangle);
		shapes.add(firstCircle);
		AreaCalculator areaCalculator=new AreaCalculator();
		System.out.println(areaCalculator.area(shapes));
	}

}
