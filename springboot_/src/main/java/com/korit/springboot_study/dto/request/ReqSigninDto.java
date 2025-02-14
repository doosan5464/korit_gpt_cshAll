package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqSigninDto {
    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "영어 대소문자 (A-Z, a-z), 숫자 (0-9), 밑줄(_)만 포함 가능합니다.")
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "User12345678!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+~`|{}:;'<>,.?/-])[A-Za-z\\d!@#$%^&*()_+~`|{}:;'<>,.?/-]{8,}$", message = "영어 대소문자, 숫자, 특수문자(!@#$%^&*()_+~`|{}:;'<>,.?/-)를 하나 이상 모두 포함하며 8자리 이상 입력해야합니다.")
    private String password;
}