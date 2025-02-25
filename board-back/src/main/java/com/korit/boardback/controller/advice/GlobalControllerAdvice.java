package com.korit.boardback.controller.advice;

import com.korit.boardback.exception.DuplicatedValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestControllerAdvice : @ResponseBody가 포함된 형태라 JSON으로 응답을 반환
public class GlobalControllerAdvice {

    @ExceptionHandler(DuplicatedValueException.class)
    public ResponseEntity<?> duplicatedException(DuplicatedValueException e) {
        return ResponseEntity.badRequest().body(e.getFieldErrors());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class) // 인증 실패 시 발생하는 예외
    public ResponseEntity<?> badCredentialsException(BadCredentialsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}