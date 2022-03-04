package com.ait.ocpb;
import java.util.ArrayList;

//ocp correct solution
public class AreaCalculator {
	ArrayList<Shape> shapes;
	public double area(ArrayList<Shape> shapes){
		double area=0;
		this.shapes=shapes;
		for (Shape s: shapes){
		    area+=s.area();
		}
		return area;
	}
}
