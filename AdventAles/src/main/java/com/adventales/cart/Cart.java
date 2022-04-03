package com.adventales.cart;

import java.util.ArrayList;
import java.util.List;

import com.adventales.entities.Calendar;

public class Cart {

	private final List<CartItem> cartItems = new ArrayList<CartItem>();
	
	
	public void addItem(final Calendar cal, final int qty) {
		final CartItem cartItem = new CartItem(cal, qty);
		cartItems.add(cartItem);
	}
	
	public double getCartPrice() {
		double totalPrice = 0.0;
		for (CartItem cartItem : cartItems) {
			totalPrice += cartItem.getCalendar().getCost() * cartItem.getQuantity();
		}
		
		return totalPrice;
	}
}