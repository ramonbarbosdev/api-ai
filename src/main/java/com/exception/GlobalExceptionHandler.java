package com.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConversationLimitException.class)
    public ResponseEntity<?> handleConversationLimit(ConversationLimitException ex) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "timestamp", Instant.now(),
                        "status", 400,
                        "message", ex.getMessage()
                ));

    }

}