package com.ait.control;

import com.ait.exception.MedAvailException;

public interface MedAvailController {
	void processPrescription(long customerAccountId, long prescriptionId) throws MedAvailException;
}
