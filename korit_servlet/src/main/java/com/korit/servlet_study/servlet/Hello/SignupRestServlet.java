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
    private AuthService authService;

    public SignupRestServlet() {
        authService = AuthService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestJsonData = new StringBuilder();
        try(BufferedReader bufferedReader = request.getReader()) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                requestJsonData.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        SignupDto signupDto = objectMapper.readValue(requestJsonData.toString(), SignupDto.class);

        ResponseDto<?> responseDto = authService.signup(signupDto);
        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(responseDto));

    }
}