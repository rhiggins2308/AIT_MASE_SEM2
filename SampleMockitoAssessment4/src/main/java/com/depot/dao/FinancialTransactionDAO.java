package com.depot.dao;

import java.sql.SQLException;
import java.util.List;

import com.depot.dto.TransactionDto;
import com.depot.exceptions.MusicDepotException;

public interface FinancialTransactionDAO {

	List<TransactionDto> retrieveUnSettledTransactions(String clientId) throws SQLException;

}
