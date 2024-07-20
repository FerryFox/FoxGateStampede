package com.fox.gaea.configuration.security.exceptions;
public class MyInvalidTokenException extends RuntimeException
{
    public MyInvalidTokenException(String message)
    {
        super(message);
    }
}