package com.pollra.web.exception;

public abstract class TodoOutputException extends TodoException {
    public TodoOutputException() {
        super();
    }

    public TodoOutputException(String message) {
        super(message);
    }
}
