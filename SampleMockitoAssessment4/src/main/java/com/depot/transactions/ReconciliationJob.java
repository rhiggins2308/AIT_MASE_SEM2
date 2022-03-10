package com.depot.transactions;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ait.paypal.PayPalFacade;
import com.depot.dao.FinancialTransactionDAO;
import com.depot.dao.MembershipDAO;
import com.depot.dto.MembershipStatusDto;
import com.depot.dto.PaymentAdviceDto;
import com.depot.dto.TransactionDto;
import com.depot.exceptions.MusicDepotDAOException;
import com.depot.exceptions.MusicDepotException;
import com.depot.exceptions.MusicDepotPayPalException;

public class ReconciliationJob {
	private final FinancialTransactionDAO financialTxDAO;
	private final MembershipDAO membershipDAO;
	private final PayPalFacade payPalFacade;

	public ReconciliationJob(FinancialTransactionDAO financialTxDAO,
			MembershipDAO membershipDAO, PayPalFacade payPalFacade) {
		super();
		this.financialTxDAO = financialTxDAO;
		this.membershipDAO = membershipDAO;
		this.payPalFacade = payPalFacade;
	}

	public int reconcile(String clientId) throws MusicDepotException {
		List<TransactionDto> unSettledTxs;
		MembershipStatusDto membership;
		double payableAmount;
		try {
			unSettledTxs = financialTxDAO.retrieveUnSettledTransactions(clientId);
			if (unSettledTxs.size() != 0) {
				membership = membershipDAO // deduction
						.getStatusFor(clientId);
				double totalTxAmount = 0.00;
				for (TransactionDto transactionDto : unSettledTxs) {
					totalTxAmount += transactionDto.getAmount();
				}
				payableAmount = totalTxAmount - totalTxAmount * membership.getDeductable();
				PaymentAdviceDto paymentAdviceDto = new PaymentAdviceDto(payableAmount, membership.getClientPayPalId(),
						null);
				sendDataToPayPal(membership, clientId, payableAmount);
				membershipDAO.setPaymentAdvice(paymentAdviceDto);
			}
		} catch (SQLException e) {
			throw new MusicDepotDAOException();
		}
		return unSettledTxs.size();
	}
	
	private void sendDataToPayPal(MembershipStatusDto membership, String clientId, double payableAmount) throws MusicDepotException  {
		try {
			DecimalFormat f= new DecimalFormat("##.00");
			String formatPayableAmount=f.format(payableAmount);
			payPalFacade.sendAdvice(membership.getClientPayPalId(),
					"Sent payment for " + clientId + " of " + formatPayableAmount + " euro");
		} catch (MusicDepotPayPalException e) {
			throw new MusicDepotPayPalException();
		}
	}

}
