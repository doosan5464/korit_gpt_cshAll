package com.korit.boardback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;
    private LocalDateTime createdAt;

    private Role role;
}