package com.example.YouTube.exception;

public class PlaylistNoAccessException extends RuntimeException {

    public PlaylistNoAccessException(String message) {
        super(message);
    }
}
