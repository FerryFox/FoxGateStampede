package com.fox.gaea.configuration.security.exceptions;

public class RefreshTokenMissingException extends RuntimeException {

    public RefreshTokenMissingException(String message) {
        super(message);
    }
}