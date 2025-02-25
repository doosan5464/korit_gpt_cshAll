package com.korit.boardback.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원가입 정보 DTO") // @Schema 는 스웨거 전용, 별 의미 없다
public class ReqJoinDto {
    @Schema(description = "사용자이름")
    private String username;
    @Schema(description = "사용자 이메일 주소")
    private String email;
    @Schema(description = "비밀번호")
    private String password;
}