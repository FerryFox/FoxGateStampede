package com.fox.gaea.configuration.security.exceptions;

public class WrongUserException extends RuntimeException {

    public WrongUserException(String message) {
        super(message);
    }
}