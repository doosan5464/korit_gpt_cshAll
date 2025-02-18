package com.korit.springboot_security.dto.request.auth;

import lombok.Data;

@Data
public class ReqSigninDto {
    private String username;
    private String password;
}
