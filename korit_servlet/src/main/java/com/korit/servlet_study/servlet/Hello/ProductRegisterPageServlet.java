package com.korit.servlet_study.servlet.Hello;

import com.korit.servlet_study.datas.DataList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// http://localhost:8080/servlet_study_war/product/register
// (GET)
@WebServlet("/product/register") // 이 서블릿은 "/product/register" URL 패턴에 매핑됨
public class ProductRegisterPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 서블릿 초기화 시 호출됨
        // ServletContext는 애플리케이션 전체에서 공유되는 객체()
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("serverName", "서블릿 학습"); // serverName이라는 속성을 ServletContext에 추가
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("상품으로 페이지 doGet 호출");

        // GET 요청이 들어왔을 때 호출되는 메소드
//        HttpSession session = req.getSession(); // HttpSession 객체를 가져옴 (사용자 Session)
//        session.setAttribute("username", "junil"); // HttpSession에 "username" 속성을 "junil"로 설정

        // request 객체에 "categories" 속성으로 DataList에서 카테고리 목록을 가져와서 저장
        req.setAttribute("categories", DataList.getCategoryList());

        // /WEB-INF/product_register.jsp 페이지로 포워딩(서블릿이 직접 응답을 처리하지 않고 JSP로 넘김)
        req.getRequestDispatcher("/WEB-INF/product_register.jsp").forward(req, resp);
        // "/WEB-INF" 폴더 아래의 JSP 페이지는 직접 접근할 수 없고, 서블릿을 통해서만 접근 가능
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 입력문자 처리?

        // 클라이언트가 보낸 <form> 데이터에서 name 속성이 "parameterName"인 값을 가져옴
        System.out.println(req.getParameter("category"));
        System.out.println(req.getParameter("productName"));
        System.out.println(req.getParameter("price"));
        System.out.println(req.getParameter("registerDate"));
        resp.setStatus(200); // http 프로토콜 상태 코드
        resp.setContentType("text/html"); // 응답하는 content 타입을 바꿈

        resp.getWriter().println("{\"name\":\"김준일\"}");


        resp.getWriter().println(
                "<script>" +
                        "alert(\"등록이 완료되었습니다.\");" +
                        "location.href='http://localhost:8080/servlet_study_war/product/register';" +
                        "</script>"
        );
    }
}