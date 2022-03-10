package com.ait.ocpa;
import java.util.ArrayList;
//ocp violation with two classes
public class AreaCalculator {
	ArrayList<Object> shapes;
	public double area(ArrayList<Object> shapes){
		double area=0;
		this.shapes=shapes;
		for (Object o: shapes){
		    if (o.getClass().equals(Rectangle.class)) {
		    	Rectangle r=(Rectangle) o;
		       area+=r.getWidth()*r.getHeight();
		    }
		    else if (o.getClass().equals(Circle.class)) {
		       Circle c=(Circle) o;
		       area+=c.getRadius()*c.getRadius()*Math.PI;
		    }	
		}
		return area;
	}
}
