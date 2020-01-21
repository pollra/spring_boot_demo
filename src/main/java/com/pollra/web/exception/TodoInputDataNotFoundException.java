package com.pollra.web.exception;

public class TodoInputDataNotFoundException extends TodoInputDataException{
    public TodoInputDataNotFoundException() {
        super();
    }

    public TodoInputDataNotFoundException(String message) {
        super(message);
    }
}
