package com.korit.springboot_study.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        UserDetails principalUser = new UserDetails() { // 사용자 정보를 저장하는 객체
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return "aa";
            }

            @Override
            public String getUsername() {
                return "bb";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principalUser, "", principalUser.getAuthorities());
        // UsernamePasswordAuthenticationToke 은 기본 인증 토큰.
        /*
        principalUser
            : 인증된 사용자 객체 (UserDetails를 구현한 객체).

        ""
            : 비밀번호를 빈 문자열로 설정 (이미 인증이 완료된 상태이므로 불필요).

        principalUser.getAuthorities()
            : 사용자의 권한(Role) 정보.
        */
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // SecurityContextHolder를 통해 현재 요청의 인증 정보를 저장
        // 위에 지금 싹다 true로 수정함. 하나라도 false면 인증 실패
        /*

        SecurityContextHolder
        : 현재 요청(스레드)의 보안 컨텍스트를 관리하는 전역 저장소
            이 안에는 SecurityContext 객체가 들어있고,
            SecurityContext에는 현재 인증된 사용자의 정보 (Authentication)가 저장됨

            setAuthentication(authenticationToken)을 호출하면 authenticationToken을 현재 요청의 인증 정보로 설정
            이후 다른 곳(필터, 컨트롤러, 서비스 등)에서 SecurityContextHolder.getContext().getAuthentication()을 호출하면 저장된 인증 정보를 꺼낼 수 있음

        SecurityContextHolder가 요청마다 유지되는 원리
        : SecurityContextPersistenceFilter 라는 필터가 내부적으로 라이브러리에 있기 때문.
            즉, 요청이 들어올 때 SecurityContextPersistenceFilter가 SecurityContext를 생성 (이전 요청에서 저장된 값이 있으면 불러옴)
            우리가 setAuthentication(authenticationToken)을 하면 SecurityContext에 저장
         */

        filterChain.doFilter(servletRequest, servletResponse);
        // 다음 필터로 요청을 넘김
        // SecurityConfig에서 http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 로 다음 필터는 기본 필터를 작동
    }
}