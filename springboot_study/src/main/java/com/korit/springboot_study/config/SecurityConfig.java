package com.korit.springboot_study.config;

import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import com.korit.springboot_study.security.filter.CustomAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity // 시큐리티 설정 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이미 모두 구현되어있는 시큐리티 패키지

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // Configuration 이지만 void. httpSecurity에서는 그냥 설정임
        http.httpBasic().disable(); // alert 로그인 금지
        http.formLogin().disable(); // 기본 로그인 금지
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // customAuthenticationFilter를 기본 로그인 필터 앞에 배치해서 JWT 인증 같은 걸 먼저 수행하고 싶을 때 사용
        // customAuthenticationFilter를 UsernamePasswordAuthenticationFilter보다 먼저 실행되도록 설정
        http.authorizeRequests()  // 요청들에 대한 권한 설정
                .antMatchers(     // 라는 경로들은
                        "/api/post/**",
                        "/api/user/**"
                )
                .permitAll()      // 다 허락
                .anyRequest()     // 나머지 모든 요청들은
                .authenticated()  // 인증 받으쇼
                .and()
                .exceptionHandling() // 인증 예외 생기면 여기서 처리할게
                .authenticationEntryPoint(customAuthenticationEntryPoint); // 여기서 예외 처리 받겠습니다
    }
}
