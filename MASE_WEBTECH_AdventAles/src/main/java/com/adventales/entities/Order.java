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
public class Order {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	private int orderShipped;
	private String userEmail;
	private double orderTotal;
    private String address1, address2, town, county;
	
    public int getOrderId() {
    	return this.orderId;
    }
   
    public int getOrderShipped() {
    	return this.orderShipped;
    }
    
    public void setOrderShipped(int orderShipped) {
    	this.orderShipped = orderShipped;
    }
    
    public String getUserEmail() {
		return userEmail;
	}
	
 	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

    public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	} 
}