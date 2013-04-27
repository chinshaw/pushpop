package com.pushpop.shared;

public class AuthenticationProviderException extends Exception {

    /**
     * Serialization Id
     */
    private static final long serialVersionUID = 5453628582550842777L;

    public AuthenticationProviderException() {
        super();
    }

    public AuthenticationProviderException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AuthenticationProviderException(String message) {
        super(message);
    }

    public AuthenticationProviderException(Throwable throwable) {
        super(throwable);
    }

}
