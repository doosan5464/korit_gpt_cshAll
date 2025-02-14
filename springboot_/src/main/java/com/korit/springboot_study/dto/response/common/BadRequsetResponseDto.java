package com.korit.springboot_study.dto.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@Getter
@ApiModel(description = "잘못된 요청 응답 DTO")
public class BadRequsetResponseDto<T> extends ResponseDto<T> {
    @ApiModelProperty(value = "HTTP 상태 코드", example = "400")
    private final int status;
    @ApiModelProperty(value = "응답 메세지", example = "잘못된 요청입니다.")
    private String message;

    public BadRequsetResponseDto(T data) {
        super(data);
        status = 400;
        message = "잘못된 요청입니다.";
    }
}
