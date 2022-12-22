package com.example.YouTube.exception;

public class ProfileIdNotFound extends RuntimeException{
    public ProfileIdNotFound(String message) {
        super(message);
    }
}
