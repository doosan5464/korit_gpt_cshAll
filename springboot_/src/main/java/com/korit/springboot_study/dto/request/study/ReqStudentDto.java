package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel(description = "학생정보 조회 학습 DTO") // Dto 위의 어노테이션 설명문은 ApiModel
public class ReqStudentDto {
    @NonNull // 빈값이 아니게끔?
    @ApiModelProperty(value = "학생 이름", example = "김준일", required = true) // model.데이터 위의 어노테이션 설명문은 ApiModelProperty
    private String name;
    @ApiModelProperty(value = "학생 나이", example = "32", required = false)
    private int age;
}
