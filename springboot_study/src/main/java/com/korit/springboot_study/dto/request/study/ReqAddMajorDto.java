package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddMajorDto {
    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    private String majorName;
}
