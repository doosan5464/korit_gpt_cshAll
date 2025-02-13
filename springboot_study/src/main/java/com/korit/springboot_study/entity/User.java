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

    // security의 PrincipalUser, int 인 이유 : db에서는 int로 저장함. 그러면 가져올때 boolean으로 쓸 수 있어서
    private int isAccountNonExpired;
    private int isAccountNonLocked;
    private int isCredentialsNonExpired;
    private int isEnabled;
}
