package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {

    @Override
    // 사용자가 브라우저에서 URL을 통해 데이터를 요청할 때 호출 (조회)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = User.builder()
                .username("test")
                .password("1234")
                .name("테스트")
                .email("test@gmail.com")
                .build();

        String jsonUser = objectMapper.writeValueAsString(user);
        // user 객체를 JSON 문자열로 변환

        System.out.println(jsonUser);
        // {"userId":0,"username":"test","password":"1234","name":"테스트","email":"test@gmail.com"}

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 대충 react에 허가해주는 그런거


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
