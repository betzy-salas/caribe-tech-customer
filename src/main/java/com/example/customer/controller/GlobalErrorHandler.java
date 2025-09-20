package com.example.customer.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,Object>> handle(ResponseStatusException exception, HttpServletRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", java.time.OffsetDateTime.now().toString());
        body.put("status", exception.getStatusCode().value());
        body.put("error", exception.getCause());
        body.put("message", exception.getReason());
        body.put("path", request.getRequestURI());
        return ResponseEntity.status(exception.getStatusCode()).body(body);
    }
}

