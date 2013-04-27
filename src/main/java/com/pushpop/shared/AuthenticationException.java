package com.pushpop.shared;

public class AuthenticationException extends Exception {
    
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
        super();
    }
    
    public AuthenticationException(String message) {
        super(message);
    }
    
    public AuthenticationException(String message, Throwable throwable) {
    	super(message, throwable);
    }

}
