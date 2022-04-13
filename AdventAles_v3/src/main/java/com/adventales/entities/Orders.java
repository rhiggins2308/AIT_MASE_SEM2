package com.adventales.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Orders {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	private int userId;
	
	//@NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name="orderdate")
    private Date orderdate;
    private double itemsAmt;
    private double shippingAmt;
    private double orderTotal;
	
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public double getItemsAmt() {
		return itemsAmt;
	}
	public void setItemsAmt(double itemsAmt) {
		this.itemsAmt = itemsAmt;
	}
	public double getShippingAmt() {
		return shippingAmt;
	}
	public void setShippingAmt(double shippingAmt) {
		this.shippingAmt = shippingAmt;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}    
}