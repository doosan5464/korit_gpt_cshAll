package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ReqSignupDto {
    /*
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    못함. 왜???

    DTO는 단순 데이터 전달용 객체 (POJO)

    @Component, @Service, @Repository 같은 빈 등록 어노테이션이 없음
    그래서 @Autowired를 사용해도 Spring이 주입해 줄 수 없음
    Spring 컨테이너가 관리하는 객체가 아님

    @Autowired는 Spring 컨테이너에 의해 관리되는 빈(Bean)에서만 동작
    ReqSignUpDto는 @RestController에서 요청이 들어올 때 new로 생성됨
    즉, Spring이 생성한 객체가 아니라서 @Autowired가 적용되지 않음


    그래서 밑에 toUser. toEntity에 매개변수로 맏는다

     */
    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "영어 대소문자 (A-Z, a-z), 숫자 (0-9), 밑줄(_)만 포함 가능합니다.")
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "User12345678!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+~`|{}:;'<>,.?/-])[A-Za-z\\d!@#$%^&*()_+~`|{}:;'<>,.?/-]{8,}$", message = "영어 대소문자, 숫자, 특수문자(!@#$%^&*()_+~`|{}:;'<>,.?/-)를 하나 이상 모두 포함하며 8자리 이상 입력해야합니다.")
    private String password;

    @ApiModelProperty(value = "성명", example = "홍길동", required = true)
    @Pattern(regexp = "^[가-힇]{2,}$", message = "한글 2자 이상만 입력가능합니다.")
    private String name;

    @ApiModelProperty(value = "이메일주소", example = "user@mail.com", required = true)
    @Email(message = "이메일 형식으로 입력해야합니다.")
    private String email;

    public User toUser(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }
}