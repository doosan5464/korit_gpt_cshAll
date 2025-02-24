package com.korit.boardback.controller.advice;

import com.korit.boardback.exception.DuplicatedValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DuplicatedValueException.class)
    public ResponseEntity<?> duplicatedException(DuplicatedValueException e) {
        return ResponseEntity.badRequest().body(e.getFieldErrors());
    }

}