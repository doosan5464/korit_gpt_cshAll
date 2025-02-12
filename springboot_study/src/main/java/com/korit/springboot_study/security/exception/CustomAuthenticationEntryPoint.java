package com.korit.springboot_study.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// SecurityConfig.java에서 예외 핸들러에 넣을 클래스?
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 인증 예외 발생 시 실행되는 메서드 (Spring Security에서 인증 실패 시 호출됨)

        System.out.println("개 레전드 인증 예외 발생"); // 예외 발생 로그 출력
        authException.printStackTrace(); // 예외 상세 정보 출력

        response.setStatus(403); // HTTP 응답 상태 코드 403 (Forbidden) 설정
        response.setContentType("application/json"); // 응답을 JSON 형식으로 설정
        response.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지를 위해 UTF-8 인코딩 설정

        // 클라이언트에게 JSON 응답 전송 (Map.of 사용해 {"error": "hey hey 인증 필요~!~!"} 형태로 변환)
        response.getWriter().write(objectMapper.writeValueAsString(Map.of("error", "hey hey 인증 필요~!~!")));
    }

}