package com.adventales.entities;

public enum CalSize {

	TWELVE(12),
	TWENTY_FOUR(24);
	
	private int size;
	
	private CalSize(int size) {
		this.size = size;
	}
	
	public int getCalSize() {
		return this.size;
	}
}