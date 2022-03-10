package com.tus.service;

import com.tus.exception.ArtGalleryException;

public interface TransactionProcessing {
	int processTransactions(String artistId, String securityToken) throws ArtGalleryException;
}
