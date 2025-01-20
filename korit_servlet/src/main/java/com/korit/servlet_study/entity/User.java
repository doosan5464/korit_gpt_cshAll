package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User { // sql에서 받아 저장할 데이터
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;
}