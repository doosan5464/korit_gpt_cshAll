package com.korit.springboot_study.config;

import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import com.korit.springboot_study.security.filter.CustomAuthenticationFilter;
import com.korit.springboot_study.security.filter.JwtAuthenticationFilter;
import com.korit.springboot_study.security.oauth2.OAuth2Service;
import com.korit.springboot_study.security.oauth2.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 시큐리티 설정 적용
// 여기가 JWT 시작점?
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이미 모두 구현되어있는 시큐리티 패키지

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean // Bean으로 등록 (메서드명이 컴포넌트 이름이 됨), Config~ - Bean 이니까 이건 실행과 동시에 생성
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception { // Configuration 이지만 void. httpSecurity에서는 그냥 설정임
        http.cors(); // cors를 쓰겠다 (이게 뭔데???)
        http.csrf().disable(); // CSRF : 웹 애플리케이션에서 CSRF 공격을 방지하기 위해 사용되는 특별한 문자열
                               // (서버는 클라이언트가 보낸 CSRF 토큰이 서버가 보낸 값과 일치하는지 확인, 사용자가 의도하지 않은 요청을 보낼 수 없도록 해서 CSRF 공격을 방어)
        http.httpBasic().disable(); // alert 로그인 금지
        http.formLogin().disable(); // 기본 로그인 금지

        http.sessionManagement() // 세션 안쓸려고
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // jwt 로그인 권한을 상시 유지하지 않겠다

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // jwtAuthenticationFilter를 기본 로그인 필터 앞에 배치해서 JWT 인증 같은 걸 먼저 수행하고 싶을 때 사용
        // jwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter보다 먼저 실행되도록 설정

        http.oauth2Login() // 구글 로그인 라이브러리
                .successHandler(oAuth2SuccessHandler) //
                        .userInfoEndpoint()
                                .userService(oAuth2Service);

        http.exceptionHandling() // 인증 예외 생기면 여기서 처리할게
                .authenticationEntryPoint(customAuthenticationEntryPoint); // 여기서 예외 처리("Authorization"이 없는 경우) 받음


        http.authorizeRequests()  // 요청들에 대한 권한 설정
                .antMatchers(     // 라는 경로들은
                        "/swagger-ui/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/server/hc"
                )
                .permitAll()      // 다 허락

                .antMatchers(
                        "/api/auth/**"
                )
                .permitAll()

                .antMatchers(HttpMethod.GET, "/api/post/**")
                .permitAll()

                .anyRequest()      // 나머지 모든 요청들은
                .authenticated();  // 인증을 받아야 함

    }
}