package com.adventales.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="calendars")
public class Calendar {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int calId;
	private String calType;
	private int calSize;
	private double cost;
	
	public Calendar() {}
	public Calendar(int calId, CalType calType, CalSize calSize, double cost) {
		this.calId = calId;
		this.calType = calType.getCalType();
		this.calSize = calSize.getCalSize();
		this.cost = cost;
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
}