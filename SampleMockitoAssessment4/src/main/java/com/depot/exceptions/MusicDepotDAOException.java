package com.depot.exceptions;

public class MusicDepotDAOException extends MusicDepotException {

	private static final long serialVersionUID = 334051992916748022L;

	public MusicDepotDAOException() {
		super("Error in connection to MusicDepot database");
	}

}
