package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/authenticated") // 정보 수정
public class AuthenticatedServlet extends HttpServlet {
    private JwtProvider jwtProvider;

    public AuthenticatedServlet() {
        jwtProvider = JwtProvider.getInstance();
    }

    @JwtValid // 가 필요함
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization"); // jwt에서 만드는 Token은 앞에 bearer를 붙임. 명시적
        // Header에서 가져옴 (Authorization라는 키를)

        ObjectMapper objectMapper = new ObjectMapper();
        Claims claims = jwtProvider.parseToken(bearerToken);
        ResponseDto<?> responseDto = ResponseDto.success(claims.get("userId"));

        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
        // 여기까지 하면 App.js로 리턴을 줌 (JSON형태로)
    }
}