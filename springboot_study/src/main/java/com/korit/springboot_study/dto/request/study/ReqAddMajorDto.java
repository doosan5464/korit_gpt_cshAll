package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddMajorDto {
    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "입력한 학과명의 처음부터 끝까지 한글만 포함해야 하며, 공백, 숫자, 영어, 특수문자는 허용되지 않음")
    private String majorName;

    // 밑에 2개는 @Valid에서 출력이 어떻게 되는지 보여줄려고 넣은거지 그것말고는 아무런 기능을 하고 있지 않음
    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "입력한 학과명의 처음부터 끝까지 한글만 포함해야 하며, 공백, 숫자, 영어, 특수문자는 허용되지 않음")
    private String majorName2;
    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "입력한 학과명의 처음부터 끝까지 한글만 포함해야 하며, 공백, 숫자, 영어, 특수문자는 허용되지 않음")
    private String majorName3;
}
