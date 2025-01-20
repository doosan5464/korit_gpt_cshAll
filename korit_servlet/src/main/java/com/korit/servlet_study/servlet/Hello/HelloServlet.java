package com.korit.servlet_study.servlet.Hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// tomcat 서버위에 있다?
@WebServlet("/hello") // 주소로 들어가면 밑에 함수들 호출
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
        // hello만 쳐도 원래 못들어가는 파일 경로로 들어가진다

//        String name = "name";
//        resp.getWriter().println(
//                "<html>"+
//                        "<head>"+
//                        "<title>Hello Servlet</title>"+
//                        "</head>"+
//                        "<body>"+
//                        "<h1>hello servlet\t"+ name +"</h1>"+
//                        "</body>"+
//                        "</html>");
        // html 적용, 과거
    }
}