package com.korit.servlet_study.filter.server_flow;

import javax.servlet.FilterChain;

public class TestFilter {

    public static void doFilter(Request req, Response res) {
        System.out.println("전처리");
        TestServlet.doGet(req, res);
        System.out.println("후처리");
    }
}
