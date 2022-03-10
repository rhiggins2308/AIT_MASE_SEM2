package com.tus.dao;

import java.sql.SQLException;
import java.util.List;

import com.tus.entity.Artist;
import com.tus.entity.Painting;
import com.tus.entity.TransactionDto;

public interface ArtGalleryDAO {
	Artist getArtistForId(String artistId) throws SQLException;
	Painting getPaintingForId(String paintingId);
	List<TransactionDto> retrieveUnSettledTransactions(String artistIdentity) throws SQLException ;

}
