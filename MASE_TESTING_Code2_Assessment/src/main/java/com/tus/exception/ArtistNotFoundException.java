package com.tus.exception;

public class ArtistNotFoundException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public ArtistNotFoundException(final String id) {
		super("Artist with Id "+ id+" not found");
	}

}
