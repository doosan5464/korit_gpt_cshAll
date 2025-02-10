package com.korit.springboot_study.dto.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter

@ApiModel(description = "응답 DTO")
public class ResponseDto<T> {
    @ApiModelProperty(value = "응답 시간", example = "2000-01-01T12:00:00.00")
    private final String timestamp;
    @ApiModelProperty(value = "응답 데이터")
    private final T data;

    public ResponseDto(T data) {
        timestamp = String.valueOf(LocalDateTime.now());
        this.data = data;
    }
}
