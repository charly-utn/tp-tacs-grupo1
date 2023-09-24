package org.tptacs.domain.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("invalid token");
    }
}
