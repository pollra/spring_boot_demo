package com.pollra.web.exception;

public class TodoCannotExistException extends TodoInputDataException {

    public TodoCannotExistException() {
        super();
    }

    public TodoCannotExistException(String message) {
        super(message);
    }
}
