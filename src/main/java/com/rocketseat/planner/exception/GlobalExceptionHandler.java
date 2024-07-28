package com.rocketseat.planner.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        String error = "Resource Not Found";
        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(),
                status,
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

    // Bad Request
    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e , HttpServletRequest request) {
        int status = HttpStatus.BAD_REQUEST.value();
        String error = "Bad Request";
        String message = String.format("Invalid input: expected type '%s' but received value '%s'.",
                Objects.requireNonNull(e.getRequiredType()).getSimpleName(), e.getValue());

        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

}
