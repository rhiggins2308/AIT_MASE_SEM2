package com.library.app.commontests.utils;

import static com.library.app.commontests.utils.JsonTestUtils.*;

import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResourceClient {
	private URL urlBase;
	private String resourcePath;

	public ResourceClient(final URL urlBase) {
		this.urlBase = urlBase;
	}

	public ResourceClient resourcePath(final String resourcePath) {
		this.resourcePath = resourcePath;
		return this;
	}

	public Response postWithFile(final String fileName) {
		return postWithContent(getRequestFromFileOrEmptyIfNullFile(fileName));
	}

	public Response postWithContent(final String content) {
		return buildClient().post(Entity.entity(content, MediaType.APPLICATION_JSON));
	}
	
	public Response putWithFile(final String fileName) {
		return putWithContent(getRequestFromFileOrEmptyIfNullFile(fileName));
	}
	
	public Response putWithContent(final String content) {
		return buildClient().put(Entity.entity(content, MediaType.APPLICATION_JSON));
	}
	
	public void delete() {
		buildClient().delete();
	}




	public Response get() {
		return buildClient().get();
	}

	private Builder buildClient() {
		final Client resourceClient = ClientBuilder.newClient();
		return resourceClient.target(getFullURL(resourcePath)).request();
	}

	private String getFullURL(final String resourcePath) {
		try {
			return this.urlBase.toURI() + "api/" + resourcePath;
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private String getRequestFromFileOrEmptyIfNullFile(final String fileName) {
		if (fileName == null) {
			return "";
		}
		return readJsonFile(fileName);
	}
}