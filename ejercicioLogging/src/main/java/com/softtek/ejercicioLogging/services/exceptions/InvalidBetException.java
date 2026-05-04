package com.softtek.ejercicioLogging.services.exceptions;

public class InvalidBetException extends RuntimeException {
    public InvalidBetException() {
    }
    public InvalidBetException(String message) {
        super(message);
    }
}
