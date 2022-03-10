package com.tus.exception;

public class ArtGallerySecurityException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public ArtGallerySecurityException() {
		super("Authentication Failed");
	}

}
