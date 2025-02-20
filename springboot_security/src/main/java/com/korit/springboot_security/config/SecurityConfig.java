package com.korit.springboot_security.config;

import com.korit.springboot_security.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Spring Security 설정을 위한 설정 클래스
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //  CORS(Cross-Origin Resource Sharing)를 기본 설정으로 활성화
        http.cors(Customizer.withDefaults());

        // CSRF(Cross-Site Request Forgery) 보호 비활성화
        http.csrf(csrf -> csrf.disable());

        // 기본 제공하는 Form 로그인 비활성화 (JWT 사용 시 필요)
        http.formLogin(form -> form.disable());

        // 로그아웃 기능 비활성화 (JWT 방식에서는 불필요)
        http.logout(logout -> logout.disable());

        // HTTP Basic 인증 비활성화 (JWT 사용 시 필요 없음)
        http.httpBasic(httpBasic -> httpBasic.disable());

        // 세션 사용하지 않도록 설정 (STATELESS 모드: JWT 기반 인증을 위한 설정)
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // ?
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 요청별 접근 권한 설정
        http.authorizeHttpRequests(auth ->
                auth
                        // Swagger UI 및 API 문서 관련 엔드포인트는 인증 없이 접근 가능
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**")
                        .permitAll()

                        // 인증 없이 접근 가능한 엔드포인트 설정 (예: 로그인, Redis 관련 API)
                        .requestMatchers("/api/auth/**", "/api/redis/**")
                        .permitAll()

                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
        );

        return http.build(); // 설정 적용 후 SecurityFilterChain 반환
    }
}