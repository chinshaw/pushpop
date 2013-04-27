package com.pushpop.shared;

public class PushPopException extends Exception {

    /**
     * Serialization Id
     */
    private static final long serialVersionUID = 5453628582550842777L;

    public PushPopException() {
        super();
    }

    public PushPopException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PushPopException(String message) {
        super(message);
    }

    public PushPopException(Throwable throwable) {
        super(throwable);
    }

}
