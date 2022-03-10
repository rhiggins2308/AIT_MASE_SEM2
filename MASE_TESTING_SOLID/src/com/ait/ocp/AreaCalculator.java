package com.ait.ocp;
import java.util.ArrayList;
//ocp violation with on shape
public class AreaCalculator {
	ArrayList<Rectangle> shapes;
	public double area(ArrayList<Rectangle> shapes){
		double area=0;
		this.shapes=shapes;
		for (Rectangle r: shapes){
			area += r.getWidth()*r.getHeight();
		}
		return area;
	}
}
