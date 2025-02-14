package com.korit.springboot_study.security.principal;

import com.korit.springboot_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class PrincipalUser implements UserDetails {
    private User user;

    // DB에 전부 컬럼으로 있어야 한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // GrantedAuthority의 자식 객체들만
        return user.getUserRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 여기부터 User Entity 클래스에 다 넣어야 함
    @Override
    // 계정 사용 기간 만료 여부
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired() == 1;
    }

    @Override
    // 계정 잠금 여부
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked() == 1;
    }

    @Override
    // 계정 인가 여부
    public boolean isCredentialsNonExpired() {
        return user.getIsCredentialsNonExpired() == 1;
    }

    @Override
    // 계정 활성 여부
    public boolean isEnabled() {
        return user.getIsEnabled() == 1;
    }
}