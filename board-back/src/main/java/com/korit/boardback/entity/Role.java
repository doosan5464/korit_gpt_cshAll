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
public class Role {
    private int roleId;
    private String roleName;
    private String roleNameKor;
    private LocalDateTime createdAt;
}