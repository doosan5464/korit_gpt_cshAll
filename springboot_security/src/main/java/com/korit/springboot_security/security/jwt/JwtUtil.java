package com.korit.springboot_security.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

    private Key key; // JWT 서명에 사용할 비밀 키
    private Long accessTokenExpire; // 액세스 토큰 만료 시간
    private Long refreshTokenExpire; // 리프레시 토큰 만료 시간

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // 비밀 키를 바이트 배열로 변환하여 설정
        accessTokenExpire = 1000l * 60 * 60; // 액세스 토큰 만료 시간 (1시간)
        refreshTokenExpire = 1000l * 60 * 60 * 24 * 7; // 리프레시 토큰 만료 시간 (7일)
    }

    // JWT 생성 메서드
    public String generateToken(String userId, String username, boolean isRefreshToken) {
        return Jwts.builder()
                .setId(userId) // JWT ID에 사용자 ID 설정
                .setSubject(username) // JWT Subject에 사용자 이름 설정
                .setExpiration(new Date(System.currentTimeMillis() + (isRefreshToken ? refreshTokenExpire : accessTokenExpire))) // 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256) // 서명 알고리즘과 비밀 키 사용
                .compact(); // JWT 생성
    }

    // JWT 파싱 메서드
    public Claims parseToken(String token) {
        Claims claims = null; // Claims 객체 초기화
        try {
            claims = Jwts.parser()
                    .setSigningKey(key) // 서명 검증을 위한 비밀 키 설정
                    .parseClaimsJws(token) // JWT 파싱
                    .getBody(); // JWT 본문 추출
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 에러 출력
        }
        return claims; // 파싱된 Claims 반환
    }
}
