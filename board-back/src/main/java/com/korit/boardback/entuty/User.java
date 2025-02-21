package com.korit.boardback.entuty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String oAuth2Name;
    private String oAuth2Provider;
    private int accountExpired;
    private int accountLocked;
    private int credentialsExpired;
    private int accoutEnabled;
    private LocalDateTime createdAt;
}
