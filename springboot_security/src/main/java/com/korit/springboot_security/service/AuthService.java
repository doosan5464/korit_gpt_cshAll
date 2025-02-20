package com.korit.springboot_security.service;

import com.korit.springboot_security.dto.request.auth.ReqSigninDto;
import com.korit.springboot_security.dto.response.RespAuthDto;
import com.korit.springboot_security.entity.User;
import com.korit.springboot_security.repository.UserRepository;
import com.korit.springboot_security.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service // 해당 클래스가 서비스 레이어의 빈으로 등록됨
public class AuthService {

    @Autowired
    private UserRepository userRepository; // 사용자 정보를 조회하기 위한 JPA Repository

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 검증을 위한 BCrypt 인코더

    @Autowired
    private JwtUtil jwtUtil; // JWT 토큰 생성 및 검증 유틸리티 클래스

    @Autowired
    private RedisTokenService redisTokenService; // 로그인 시 액세스 토큰과 리프레시 토큰을 Redis에 저장하는 서비스


    // 로그인
    public RespAuthDto login(ReqSigninDto reqSigninDto) {
        // 사용자명으로 데이터베이스에서 사용자 정보 조회 (존재하지 않으면 예외 발생)
        User foundUser = userRepository.findByUsername(reqSigninDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요.")); // 없으면 예외 발생

        // 입력한 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
        if (!bCryptPasswordEncoder.matches(reqSigninDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요."); // 비밀번호 불일치 시 예외 발생
        }

        // 액세스 토큰 생성 (isRefresh = false) - 인증된 사용자를 위한 토큰
        String accessToken = jwtUtil
                .generateToken(Integer.toString(
                        foundUser.getUserId()), // userId를 문자열로 변환하여 토큰에 포함
                        foundUser.getUsername(), // username 포함
                        false); // 액세스 토큰이므로 isRefresh=false

        // 리프레시 토큰 생성 (isRefresh = true) - 액세스 토큰 갱신을 위한 토큰
        String refreshToken = jwtUtil
                .generateToken(Integer.toString(
                        foundUser.getUserId()), // userId를 문자열로 변환하여 토큰에 포함
                        foundUser.getUsername(), // username 포함
                        true); // 리프레시 토큰이므로 isRefresh=true

        // 생성된 액세스 토큰을 Redis에 저장 (유효기간: 60분)
        redisTokenService.setAccess(reqSigninDto.getUsername(), accessToken, Duration.ofMinutes(60));
        // 생성된 리프레시 토큰을 Redis에 저장 (유효기간: 7일)
        redisTokenService.setRefresh(reqSigninDto.getUsername(), refreshToken, Duration.ofDays(7));

        // 생성된 토큰을 포함한 응답 객체 반환
        return RespAuthDto.builder()
                .accessToken(accessToken) // 액세스 토큰 포함
                .refreshToken(refreshToken) // 리프레시 토큰 포함
                .build();
    }


    // 리프레시 -> db대신 redis 저장소로 캐시로 accessToken 획득?
    public RespAuthDto refresh(String refreshToken) {
        // 리프레시 토큰을 파싱하여 Claims(토큰 정보) 추출
        Claims claims = jwtUtil.parseToken(refreshToken);
        if (claims == null) { // 토큰이 유효하지 않으면 null 반환
            return null;
        }
        String username = claims.getSubject(); // 토큰에서 사용자 이름 추출
        String userId = claims.getId(); // 토큰에서 userId 추출
        String redisRefresh = redisTokenService.getRefresh(username); // Redis에 저장된 리프레시 토큰 가져오기

        // Redis에 저장된 리프레시 토큰과 요청받은 토큰이 다르면 갱신 불가
        if (redisRefresh == null || !redisRefresh.equals(refreshToken)) {
            return null;
        }

        // 새로운 액세스 토큰 생성 (isRefresh=false)
        String newAccessToken = jwtUtil.generateToken(userId, username, false);

        // 리프레시 토큰은 만료기간을 오래 두는 이유
        // : 인증을 유지 시키고 싶기 때문. (db로 안가게)
        // 새로운 액세스 토큰과 기존 리프레시 토큰 반환
        return RespAuthDto.builder()
                .accessToken(newAccessToken) // 새로 생성된 액세스 토큰 포함
                .refreshToken(refreshToken) // 기존 리프레시 토큰 유지
                .build();
    }
}
