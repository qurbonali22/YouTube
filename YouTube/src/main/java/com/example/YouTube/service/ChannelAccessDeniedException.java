package com.example.YouTube.service;

public class ChannelAccessDeniedException extends RuntimeException {

    public ChannelAccessDeniedException(String message) {
        super(message);
    }
}
