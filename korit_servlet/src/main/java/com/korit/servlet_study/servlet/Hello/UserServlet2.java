package com.korit.servlet_study.servlet.Hello;

import com.korit.servlet_study.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user2")
public class UserServlet2 extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        List<User> users = new ArrayList<>();
        users.add(new User(0,"aaa", "1111", "aaaaaa", "aaa@gmail.com"));
        users.add(new User(0,"bbb", "1111", "bbbbbb", "bbb@gmail.com"));
        users.add(new User(0,"ccc", "1111", "cccccc", "ccc@gmail.com"));
        users.add(new User(0,"ddd", "1111", "dddddd", "ddd@gmail.com"));
        users.add(new User(0,"eee", "1111", "eeeeee", "eee@gmail.com"));

        config.getServletContext().setAttribute("users", users);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchValue = request.getParameter("searchValue");
        ServletContext servletContext = request.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");

        if(searchValue != null) {
            if(!searchValue.isBlank()) {
                request.setAttribute("users", users.stream().filter(user -> user.getUsername().contains(searchValue)).collect(Collectors.toList()));
            }
        }
        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .build();

        ServletContext servletContext = request.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
        if(users.stream().filter(u -> u.getUsername().equals(user.getUsername())).collect(Collectors.toList()).size() > 0) {
            response.setContentType("text/html");
            response.getWriter().println("<script>" +
                    "alert('이미 존재하는 사용자 이름입니다.');" +
                    "history.back();" +
                    "</script>");
            return;
        }
        users.add(user);

        response.sendRedirect("http://localhost:8080/servlet_study_war/user");
    }
}