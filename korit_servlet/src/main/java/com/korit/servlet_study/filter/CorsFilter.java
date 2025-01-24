package com.korit.servlet_study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// setHeader 여기서 자동으로
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        // ServletResponse를 HttpServletResponse로 다운캐스팅

        // CORS 정책을 허용하기 위해 응답 헤더 설정
        res.setHeader("Access-Control-Allow-Origin", "*");  // 모든 도메인에서의 요청 허용
        res.setHeader("Access-Control-Allow-Methods", "*");  // 허용하는 HTTP 메서드
        res.setHeader("Access-Control-Allow-Headers", "*");  // 허용하는 요청 헤더
        res.setHeader("Access-Control-Allow-Credentials", "true");  // 인증 정보 허용

        chain.doFilter(request, response);
        // 다음 필터로 요청을 전달하거나 최종적으로 서블릿이 요청을 처리하도록 넘김

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}