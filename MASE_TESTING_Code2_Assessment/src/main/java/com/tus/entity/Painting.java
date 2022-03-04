package com.tus.entity;

public class Painting {
	
	String paintingId;
	String title;
	double price;
	
	public Painting(String paintingId, String title, double price) {
		super();
		this.paintingId = paintingId;
		this.price = price;
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

}
