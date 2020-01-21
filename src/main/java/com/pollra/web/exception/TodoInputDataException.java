package com.pollra.web.exception;

public abstract class TodoInputDataException extends TodoException{

    public TodoInputDataException() {
        super();
    }

    public TodoInputDataException(String message) {
        super(message);
    }


}