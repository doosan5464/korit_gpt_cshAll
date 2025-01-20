package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.BookTemp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookTemp bt = BookTemp.builder()
                .bookId("1")
                .bookName("책이름뭘로하지")
                .author("노진구작가")
                .publisher("출판사")
                .category("카테고리")
                .imgUrl("대충url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터와 Java 객체 간의 변환을 처리하는 데 사용
        String jsonBt = objectMapper.writeValueAsString(bt); // JSON으로 변환?
        resp.setContentType("application/json"); // application을 json 타입으로 잡는다?
        System.out.println(jsonBt);


//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");


        resp.getWriter().println(jsonBt);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
