package com.ait.ocpb;

public class Circle extends Shape{

	private double radius;
	
	public Circle(double radius){
		this.radius=radius;
	}
	
	public double getRadius(){
		return radius;
	}
	@Override
	public double area(){
		return radius*radius*Math.PI;
	}
}
