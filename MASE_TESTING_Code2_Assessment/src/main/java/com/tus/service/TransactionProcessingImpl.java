package com.tus.service;

import java.sql.SQLException;
import java.util.List;
import com.ait.security.AuthenticationFilter;
import com.tus.dao.ArtGalleryDAO;
import com.tus.dispatch.Dispatcher;
import com.tus.entity.Artist;
import com.tus.entity.Painting;
import com.tus.entity.TransactionDto;
import com.tus.exception.ArtGalleryDAOException;
import com.tus.exception.ArtGalleryException;
import com.tus.exception.ArtGallerySecurityException;
import com.tus.exception.ArtistNotFoundException;
import com.tus.payment.PaymentFacade;

public class TransactionProcessingImpl implements TransactionProcessing{
	
	private static final int GALLERY_COMMISSION_PERCENT=10;
	private final ArtGalleryDAO artGalleryDAO;
	private PaymentFacade paymentFacade;
	private AuthenticationFilter authenticationFilter;
	private Dispatcher dispatcher;

	public TransactionProcessingImpl(ArtGalleryDAO artGalleryDAO, PaymentFacade paymentFacade,
			AuthenticationFilter authenticationFilter, Dispatcher dispatcher) {
		super();
		this.artGalleryDAO = artGalleryDAO;
		this.paymentFacade = paymentFacade;
		this.authenticationFilter=authenticationFilter;
		this.dispatcher=dispatcher;
	}

	public int processTransactions(String artistId, String securityToken) throws ArtGalleryException {
		Artist artist;

		if (!authenticationFilter.isTokenValid(securityToken)) {
			throw new ArtGallerySecurityException();
		}

		try {
			artist = artGalleryDAO.getArtistForId(artistId);
			if (artist == null) {
				throw new ArtistNotFoundException(artistId);
			}
			// check if artist is valid
			List<TransactionDto> unSettledTxs = artGalleryDAO.retrieveUnSettledTransactions(artistId);
			double totalTxAmount = 0.00;
			for (TransactionDto unSettledTxDto : unSettledTxs) {
				String paintingId = unSettledTxDto.getPaintingId();
				Painting painting = artGalleryDAO.getPaintingForId(paintingId);
				dispatcher.dispatch(painting.getTitle(), unSettledTxDto.getPurchaserId());
				double price = painting.getPrice();
				totalTxAmount += price;
			}
			if (totalTxAmount != 0) {
				totalTxAmount -= totalTxAmount * GALLERY_COMMISSION_PERCENT / 100;
				paymentFacade.pay(artistId, totalTxAmount, artist.getPaymentDetails());
			}
			return unSettledTxs.size();
		} catch (SQLException e) {
			throw new ArtGalleryDAOException();
		}

	}
}

