package com.korit.springboot_study.exception;

import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;

import java.util.Map;

public class CustomDuplicateKeyException extends DuplicateKeyException {
    // DuplicateKeyException. 중복 예외는 db에서 터지는 에러라서 우리쪽으로 돌릴 필요가 있다
    // Custom으로 만든 예외

    @Getter
    private Map<String, String> errors;

    public CustomDuplicateKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicateKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomDuplicateKeyException(String msg, Map<String, String> errors) {
        super(msg);
        this.errors = errors;
    }
}
