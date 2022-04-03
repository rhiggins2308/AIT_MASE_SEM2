package com.adventales.cart;

import com.adventales.entities.Calendar;

public class CartItem {
	private final Calendar calendar;
	private final int quantity;
	
	public CartItem(final Calendar calendar, final int quantity) {
		this.calendar = calendar;
		this.quantity = quantity;
	}
	
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	public int getQuantity() {
		return this.quantity;
	}	
}