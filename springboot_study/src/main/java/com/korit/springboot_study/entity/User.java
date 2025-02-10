package com.korit.springboot_study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    @JsonIgnore // password는 비밀이니까 안뜨게 해줌
    private String password;
    private String name;
    private String email;

    // <Role> 말고 <UserRole>이 들어와야 함. 바로 못넣음
    List<UserRole> userRoles;
}
