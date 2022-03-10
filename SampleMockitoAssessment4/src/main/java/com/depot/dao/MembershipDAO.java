package com.depot.dao;

import java.sql.SQLException;

import com.depot.dto.MembershipStatusDto;
import com.depot.dto.PaymentAdviceDto;

public interface MembershipDAO {
    //id is target id
	MembershipStatusDto getStatusFor(String id) throws SQLException;
	void setPaymentAdvice(PaymentAdviceDto paymentAdviceDto) throws SQLException;
}
