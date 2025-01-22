package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SigninDto;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/api/signin")
public class SigninRestServlet extends HttpServlet {

    private AuthService authService;

    public SigninRestServlet() {
        authService = AuthService.getInstance();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = req.getReader()) {
            String Line;

            while ((Line = reader.readLine()) != null)  {
                sb.append(Line);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        SigninDto signinDto = mapper.readValue(sb.toString(), SigninDto.class);

        ResponseDto<?> responseDto = authService.signin(signinDto);

        resp.setContentType("application/json");
        resp.setStatus(responseDto.getStatus());
        resp.getWriter().println(mapper.writeValueAsString(responseDto));

    }
}
