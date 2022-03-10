package com.ait.security;

public interface AuthenticationFilter {
	boolean isTokenValid(String token);

}
