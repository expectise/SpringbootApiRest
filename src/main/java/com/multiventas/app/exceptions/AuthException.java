package com.multiventas.app.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
	private static final long serialVersionUID = 12991298L;

	public AuthException(String msg) {
		super(msg);
	}

}
