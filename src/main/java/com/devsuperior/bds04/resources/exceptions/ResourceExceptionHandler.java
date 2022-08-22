package com.devsuperior.bds04.resources.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError errors = new ValidationError();
        errors.setTimeStamp(Instant.now());
        errors.setStatus(status.value());
        errors.setError("Validation exception");
        errors.setMessage(e.getMessage());
        errors.setPath(request.getRequestURI());
        e.getBindingResult().getFieldErrors().stream()
                .forEach(f -> errors.addError(f.getField(),f.getDefaultMessage()));

        return ResponseEntity.status(status).body(errors);
    }
}
