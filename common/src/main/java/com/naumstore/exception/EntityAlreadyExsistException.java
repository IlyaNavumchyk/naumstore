package com.naumstore.exception;

public class EntityAlreadyExsistException extends RuntimeException{

    public EntityAlreadyExsistException() {
        super();
    }

    public EntityAlreadyExsistException(String message) {
        super(message);
    }
}
