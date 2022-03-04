package com.ait.dao;

import java.sql.SQLException;

import com.ait.entities.Customer;
import com.ait.entities.Prescription;

public interface MedAvailDAO  {
	Customer getCustomerForId(long customerAccountId) throws SQLException;
	Prescription getPrescriptionForId(long prescriptionId) throws SQLException;
	boolean checkProductInStock(long productId) throws SQLException;
	
}
