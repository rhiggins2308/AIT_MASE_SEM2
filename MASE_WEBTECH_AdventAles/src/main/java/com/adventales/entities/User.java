package com.adventales.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	private String userEmail;
	private String firstName;
	private String lastName;
	private String dob;
	private String pword;
	private int userType;

	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
/*	
	public String getFirstName() {
		return firstName;
	}
*/	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
/*	
	public String getLastName() {
		return lastName;
	}
*/	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
/*	
	public String getDob() {
		return dob;
	}
*/	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getPword() {
		return pword;
	}
	
	public void setPword(String pword) {
		this.pword = pword;
	}
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	}