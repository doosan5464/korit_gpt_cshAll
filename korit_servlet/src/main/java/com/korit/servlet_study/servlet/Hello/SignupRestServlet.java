package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class SignupRestServlet extends HttpServlet {
    private AuthService authService; // service로 JSON <-> Dto

    public SignupRestServlet() {
        authService = AuthService.getInstance(); // 서블렛 생성자로 서비스객체 바로 생성
    }

    // 회원가입과 로그인은 모두 Post
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestJsonData = new StringBuilder();

        try(BufferedReader bufferedReader = request.getReader()) {
            // 버퍼에 있는 입력값을 모두 받아옴
            String line;
            while((line = bufferedReader.readLine()) != null) {
                // 혹여나 여러개 받을까봐 while로 한 줄씩 받아서 sb에 append
                requestJsonData.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper(); // Dto클래스로 바꾸거나 JSON으로
        SignupDto signupDto = objectMapper.readValue(requestJsonData.toString(), SignupDto.class); // Dto로 변환

        ResponseDto<?> responseDto = authService.signup(signupDto); // 여기서 회원가입관련 로직처리와 동시에 responseDto의 결과값으로 받고
        response.setStatus(responseDto.getStatus()); // HTTP 응답 준비
        response.setContentType("application/json"); // 콘텐츠 영역을 JSON 으로 바꿔줌
        response.getWriter().println(objectMapper.writeValueAsString(responseDto)); // JSON으로 변환과 그걸 출력

    }
}