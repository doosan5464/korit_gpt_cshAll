package com.korit.springboot_study.controller.advice;

import com.korit.springboot_study.dto.response.common.BadRequsetResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 다른 클래스들의 error들을 여기서 처리해준다
// NotFoundException에서 error가 터지면 여기서 처리하게끔 늘 지켜본다
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class) // 다른 클래스들에서 예외는 모두 throw로 NotFoundException에 던지고 있어서 여기서 제어
    public ResponseEntity<NotFoundResponseDto<?>> notFound(NotFoundException e) {
        return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));
    }

    @ExceptionHandler(value = CustomDuplicateKeyException.class) // 다른 클래스들에서 예외는 모두 throw로 NotFoundException에 던지고 있어서 여기서 제어
    public ResponseEntity<BadRequsetResponseDto<?>> duplicateKey(CustomDuplicateKeyException e) { // CustomDuplicateKeyException는 내가 만든 Error(DuplicateKeyException)
        return ResponseEntity.status(400).body(new BadRequsetResponseDto<>(e.getErrors())); // 사용자 잘못이니까 400
    }
}
