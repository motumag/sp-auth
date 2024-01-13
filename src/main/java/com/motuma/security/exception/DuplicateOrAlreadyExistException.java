package com.motuma.security.exception;

public class DuplicateOrAlreadyExistException extends RuntimeException {
    public DuplicateOrAlreadyExistException(String message) {
        super(message);
    }
}
