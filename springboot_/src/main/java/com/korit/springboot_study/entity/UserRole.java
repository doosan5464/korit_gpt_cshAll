package com.korit.springboot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 중간 테이블
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;

    private Role role;
}
