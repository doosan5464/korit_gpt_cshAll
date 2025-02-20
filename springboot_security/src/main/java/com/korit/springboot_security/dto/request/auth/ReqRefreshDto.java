package com.korit.springboot_security.dto.request.auth;

import lombok.Data;

@Data
public class ReqRefreshDto {
    private String refreshToken;
}
