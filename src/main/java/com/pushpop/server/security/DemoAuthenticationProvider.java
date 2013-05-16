package com.pushpop.server.security;

import com.pushpop.shared.AuthenticationException;
import com.pushpop.shared.AuthenticationProviderException;

public class DemoAuthenticationProvider implements IAuthenticationProvider {

	private static String emailAddress;

	@Override
	public String authenticate(String user, String password)
			throws AuthenticationException {
		DemoAuthenticationProvider.emailAddress = user;
		return emailAddress;
	}

	@Override
	public void authenticate(String user, String password,
			AuthenticationCallback callback) throws AuthenticationException {
	}

	@Override
	public String getCommonName(String authToken)
			throws AuthenticationProviderException {
		return "Demo User";
	}

	@Override
	public String getEmail(String authToken)
			throws AuthenticationProviderException {
		return emailAddress;
	}

	@Override
	public void logout(String authToken) throws AuthenticationProviderException {
	}
}
