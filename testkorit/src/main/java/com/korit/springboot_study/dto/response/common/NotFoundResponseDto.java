package com.korit.springboot_study.dto.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@Getter
@ApiModel(description = "데이터 조회 실패 응답 DTO")
public class NotFoundResponseDto<T> extends ResponseDto<T> {
    @ApiModelProperty(value = "HTTP 상태 코드", example = "404")
    private final int status;
    @ApiModelProperty(value = "응답 메세지", example = "데이터를 찾을 수 없습니다.")
    private String message;

    public NotFoundResponseDto(T data) {
        super(data);
        status = 404;
        message = "데이터를 찾을 수 없습니다.";
    }
}
