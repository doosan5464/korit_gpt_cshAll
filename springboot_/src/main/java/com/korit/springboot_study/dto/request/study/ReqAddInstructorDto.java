package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddInstructorDto {
    @ApiModelProperty(value = "교수명", example = "머스크", required = true)
    private String instructorName;
}
