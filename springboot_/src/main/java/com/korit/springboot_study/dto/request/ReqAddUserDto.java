package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ReqAddUserDto {
    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "4~16자의 영어 대소문자, 숫자, 밑줄(_)만 사용 가능합니다.")
    private String username;
    @ApiModelProperty(value = "비밀번호", example = "User12345678!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$\n", message = "8~20자의 영어 대소문자, 숫자, 특수문자(!@#$%^&*) 포함 필수")
    private String password;
    @ApiModelProperty(value = "성명", example = "김유저", required = true)
    @Pattern(regexp = "^[가-흷]{1,}", message = "2~의 한글 포함 필수")
    private String name;
    @ApiModelProperty(value = "이메일주소", example = "user@mail.com", required = true)
    @Email(message = "") // email은 전용 어노테이션이 있음
    private String email;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}
