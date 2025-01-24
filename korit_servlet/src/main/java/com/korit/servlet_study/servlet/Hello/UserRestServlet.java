package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {
    private UserService userService;

    public UserRestServlet() {
        userService = UserService.getInstance();
    }

    @Override
    @JwtValid
    // 사용자가 브라우저에서 URL을 통해 데이터를 요청할 때 호출 (조회)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        ResponseDto<?> responseDto = userService.getUser(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(responseDto); // responseDto 객체를 JSON 문자열로 변환
        System.out.println(jsonUser);
        // {"userId":0,"username":"test","password":"1234","name":"테스트","email":"test@gmail.com"}


        resp.setContentType("application/json");
        // 클라이언트에 JSON 형식의 응답을 보냄
        // 웹 브라우저나 클라이언트가 이 응답이 JSON 데이터임을 알 수 있게 해줌

        resp.getWriter().println(jsonUser);
        // getWriter() : 응답을 출력할 수 있는 PrintWriter 객체를 반환
    }

    @Override
    // 데이터를 서버로 제출할 때 호출 (추가)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
