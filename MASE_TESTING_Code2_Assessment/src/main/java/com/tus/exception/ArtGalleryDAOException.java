package com.tus.exception;

public class ArtGalleryDAOException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public ArtGalleryDAOException() {
		super("Error connecting to database");
	}

}
