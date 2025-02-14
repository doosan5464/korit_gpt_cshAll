package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSearchPublisherDto {
    @ApiModelProperty(value = "출판사명", example = "이지스퍼블리싱", required = false)
    private String keyword = "";
}