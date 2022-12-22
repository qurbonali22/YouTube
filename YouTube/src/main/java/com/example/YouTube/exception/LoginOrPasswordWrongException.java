package com.example.YouTube.exception;

public class LoginOrPasswordWrongException extends RuntimeException {
    public LoginOrPasswordWrongException(String message) {
        super(message);
    }
}
