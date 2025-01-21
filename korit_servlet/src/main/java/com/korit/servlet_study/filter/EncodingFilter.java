package com.korit.servlet_study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter("*") // 자바 서블릿 필터를 특정 URL 패턴에 매핑, web.xml 설정 파일에 따로 필터를 등록하지 않아도 자동으로 필터가 적용
public class EncodingFilter implements Filter {
    @Override
    // 클라이언트의 요청이나 응답이 필터를 통과할 때마다 호출
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("필터 시작");
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse); // 다음 필터나 서블릿이 실행
        // doFilter()가 호출될 때, 필터 체인 객체는 현재 위치를 기억하고 다음 필터를 호출할 뿐, 다시 처음부터 호출하지 않음

        System.out.println("필터 끝");

    }

    @Override
    // 필터가 생성될 때 한 번만 호출
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    // 필터가 컨테이너에서 제거될 때 호출
    public void destroy() {
        Filter.super.destroy();
    }
}
