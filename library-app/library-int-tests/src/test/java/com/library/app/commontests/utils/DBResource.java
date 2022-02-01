package com.library.app.commontests.utils;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

@Path("/DB")
public class DBResource {
	/* This is a resource end point to remove all data from database*/

	@Inject
	private TestRepositoryEJB tesRepositoryEJB;

	@DELETE
	public void deleteAll() {
		tesRepositoryEJB.deleteAll();
	}

}