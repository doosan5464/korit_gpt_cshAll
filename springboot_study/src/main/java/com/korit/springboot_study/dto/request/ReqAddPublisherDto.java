package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Publisher;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddPublisherDto {
    @ApiModelProperty(value = "출판사명", example = "이지스퍼블리싱", required = true)
    @Pattern(regexp = "^(?=.*[가-힣])[가-힣\\d]+$", message = "입력값은 최소한 한 글자 이상의 한글을 포함해야 하며, 숫자는 한글이 포함된 경우에만 사용할 수 있습니다.")
    private String publisherName;

    public Publisher toPublisher() {
        return Publisher.builder()
                .publisherName(publisherName)
                .build();
    }
}