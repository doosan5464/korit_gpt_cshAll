package com.korit.springboot_study.dto.response.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
@ApiModel(description = "학생정보 조회 학습 응답 DTO")
public class RespStudentDto {
    @ApiModelProperty(value = "학생 ID", example = "1") // Swagger에 표시되는 예시
    private int id;
    @ApiModelProperty(value = "학생 이름", example = "홍길동")
    private String name;
    @ApiModelProperty(value = "학생 나이", example = "30")
    private int age;
}