package com.korit.springboot_security.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.springboot_security.entity.User;
import com.korit.springboot_security.repository.UserRepository;
import com.korit.springboot_security.security.jwt.JwtUtil;
import com.korit.springboot_security.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component // 해당 클래스가 Spring의 빈으로 등록됨 (필터 역할 수행)
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil; // JWT 토큰을 검증 및 파싱하는 유틸리티 클래스

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Redis에서 사용자 정보를 조회하기 위한 템플릿

    @Autowired
    private ObjectMapper objectMapper; // JSON 변환을 위한 ObjectMapper

    @Autowired
    private UserRepository userRepository; // 사용자 정보를 DB에서 조회하기 위한 Repository

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 요청 헤더에서 Authorization 정보를 가져옴
        String bearerToken = getAuthorization((HttpServletRequest) servletRequest);

        // 토큰이 유효한 경우 처리
        if (isValidToken(bearerToken)) {
            String accessToken = removeBearer(bearerToken); // "Bearer " 제거 후 순수 토큰 값만 추출
            Claims claims = jwtUtil.parseToken(accessToken); // JWT 파싱하여 클레임 정보 추출

            if (claims != null) { // 토큰이 유효한 경우
                int userId = Integer.parseInt(claims.getId()); // 토큰에서 userId 추출
                User user = getUser(userId); // Redis 또는 DB에서 사용자 정보 가져오기
                setAuthentication(user); // 인증 객체를 SecurityContextHolder에 설정
            }
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private User getUser(int userId) throws JsonProcessingException { // Redis 또는 MySQL에서 사용자 조회
        User user = null;
        Object redisUser = redisTemplate.opsForValue().get("user:" + userId); // Redis에서 사용자 정보 조회

        if (redisUser != null) { // Redis에 존재하는 경우
            user = objectMapper.readValue(redisUser.toString(), User.class); // JSON -> User 객체 변환
        } else { // Redis에 없는 경우 DB에서 조회
            user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                String jsonUser = objectMapper.writeValueAsString(user); // User 객체 -> JSON 변환
                redisTemplate.opsForValue().set("user:" + userId, jsonUser, Duration.ofMinutes(10)); // Redis에 저장 (10분)
            }
        }
        return user;
    }

    private void setAuthentication(User user) { // SecurityContext에 인증 정보 설정
        if (user == null) { // 사용자 정보가 없으면 인증 설정 안 함
            return;
        }
        PrincipalUser principalUser = new PrincipalUser(user); // PrincipalUser 객체 생성
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principalUser,
                        null,
                        principalUser.getAuthorities()); // 사용자 인증 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 인증 정보 저장
    }

    private String getAuthorization(HttpServletRequest request) { // Authorization 헤더 가져오기
        return request.getHeader("Authorization");
    }

    private boolean isValidToken(String token) { // 토큰이 "Bearer "로 시작하는지 확인
        return token != null && token.startsWith("Bearer ");
    }

    private String removeBearer(String bearerToken) { // "Bearer " 문자열 제거하여 순수 토큰 반환
        return bearerToken.substring(7);
    }
}
