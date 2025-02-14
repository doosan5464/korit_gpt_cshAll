package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

// 회원정보 수정할거임
@Data
public class ReqModifyUserDto {
    @NotNull(message = "이메일 주소를 입력해 주세요.") // 값을 입력안 할 수도 있어서
    @Email(message = "이메일 주소 형식으로 입력하세요.")
    private String email;

    public User toUser(int userId) { // 매개변수로 속성을 채울 수 있음
        return User
                .builder()
                .userId(userId) // 지금 Dto에는 없지만 요청할때 userId를 받아옴
                .email(email)
                .build();
    }
}
