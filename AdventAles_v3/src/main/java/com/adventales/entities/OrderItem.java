package com.adventales.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderItem")
public class OrderItem {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderItemId;
	private int orderId;
	private int calId;
	private String calType;
	private int calSize;
	private double cost;
	private double qty;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getCalId() {
		return calId;
	}
	
	public void setCalId(int calId) {
		this.calId = calId;
	}
	
	public String getCalType() {
		return calType;
	}
	
	public void setCalType(String calType) {
		this.calType = calType;
	}
	
	public int getCalSize() {
		return calSize;
	}
	
	public void setCalSize(int calSize) {
		this.calSize = calSize;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}
}