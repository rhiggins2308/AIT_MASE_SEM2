package com.depot.exceptions;

public class MusicDepotPayPalException extends MusicDepotException {

	private static final long serialVersionUID = 334051992916748022L;

	public MusicDepotPayPalException() {
		super("PayPal Error");
	}

}
