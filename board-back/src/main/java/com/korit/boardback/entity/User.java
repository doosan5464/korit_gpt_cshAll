package com.korit.boardback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String nickname;
    private String oAuth2Name;
    private String oAuth2Provider;
    private String profileImg;
    private int accountExpired;
    private int accountLocked;
    private int credentialsExpired;
    private int accountEnabled;
    private LocalDateTime createdAt;

    private Set<UserRole> userRoles;
}