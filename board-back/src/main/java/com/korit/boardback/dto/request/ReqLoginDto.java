package com.korit.boardback.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자 로그인 DTO")
public class ReqLoginDto {
    @Schema(description = "사용자 이름", example = "test12345")
    private String username;
    @Schema(description = "비밀번호", example = "1q2w3e4r")
    private String password;
}