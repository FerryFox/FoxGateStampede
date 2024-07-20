package com.fox.gaea.configuration.security.exceptions;

public class TokenCheckException extends RuntimeException{
    
    public TokenCheckException(String message){
        super(message);
    }
}