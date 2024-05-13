package com.blogapp12.exception;

public class UserNotPresentException extends RuntimeException {
    public UserNotPresentException(String message)
    {
        super(message);
    }
}
