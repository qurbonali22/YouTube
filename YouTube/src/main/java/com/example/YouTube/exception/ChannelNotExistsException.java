package com.example.YouTube.exception;

public class ChannelNotExistsException extends RuntimeException {

    public ChannelNotExistsException(String message) {
        super(message);
    }
}
