package com.softtek.ejercicioLogging.controllers;

import com.softtek.ejercicioLogging.services.exceptions.InvalidBetException;
import com.softtek.ejercicioLogging.services.exceptions.UserAlreadyExistsException;
import com.softtek.ejercicioLogging.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidBetException.class)
    public ResponseEntity<Map<String, String>> handleInvalidBet(InvalidBetException ex) {
        log.info("Devolviendo 400 por apuesta inválida");
        log.debug("Detalle: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        log.info("Devolviendo 404 por usuario no encontrado");
        log.debug("Detalle: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserAlreadyExistsException ex) {
        log.info("Devolviendo 409 por id de usuario duplicado");
        log.debug("Detalle: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("error", ex.getMessage()));
    }
}
