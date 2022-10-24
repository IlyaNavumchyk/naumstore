package com.naumstore.exception;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException() {
        super();
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
