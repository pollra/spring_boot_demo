package com.pollra.web.exception;

public abstract class TodoException extends RuntimeException{
    public TodoException() {
        super();
    }

    public TodoException(String message) {
        super(message);
    }
}
