package com.korit.springboot_study.controller.advice;

import com.korit.springboot_study.dto.response.common.BadRequsetResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@RestControllerAdvice // 다른 클래스들의 error들을 여기서 처리해준다
// NotFoundException에서 error가 터지면 여기서 처리하게끔 늘 지켜본다
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class) // 다른 클래스들에서 예외는 모두 throw로 NotFoundException에 던지고 있어서 여기서 제어
    public ResponseEntity<NotFoundResponseDto<?>> notFound(NotFoundException e) {
        return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));
    }

    @ExceptionHandler(value = CustomDuplicateKeyException.class)
    // 다른 클래스들에서 예외는 모두 throw로 NotFoundException에 던지고 있어서 여기서 제어
    public ResponseEntity<BadRequsetResponseDto<?>> duplicateKey(CustomDuplicateKeyException e) { // CustomDuplicateKeyException는 내가 만든 Error(DuplicateKeyException)
        return ResponseEntity.status(400).body(new BadRequsetResponseDto<>(e.getErrors())); // 사용자 잘못이니까 400
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<BadRequsetResponseDto<?>> signinError(AuthenticationException e) {
        return ResponseEntity.status(403).body(new BadRequsetResponseDto<>(e.getMessage()));
    }

    // @Valid로 발생하는 유효성 검사 예외를 처리하는 핸들러
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequsetResponseDto<?>> validation(MethodArgumentNotValidException e) {
        List<Map<String, String>> errorMap = null;

        // 유효성 검사 오류 정보 가져오기
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            // 필드별 오류 메시지를 Map 형태로 변환하여 리스트로 저장
            errorMap = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> Map.of(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        // HTTP 상태 코드 400 (Bad Request)와 함께 오류 응답 반환
        return ResponseEntity.status(400).body(new BadRequsetResponseDto<>(errorMap));
    }

    // @Validated로 발생하는 유효성 검사 예외를 처리하는 핸들러
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<BadRequsetResponseDto<?>> validation(ConstraintViolationException e) {
        return ResponseEntity.status(400).body(new BadRequsetResponseDto<>(
                e.getConstraintViolations()
                        .stream()
                        // 유효성 검사 실패한 필드 이름과 오류 메시지를 Map 형태로 변환하여 리스트로 저장
                        .map(constraintViolation -> Map.of(constraintViolation.getPropertyPath(), constraintViolation.getMessage()))
                        .collect(Collectors.toList())
        ));
    }

}
