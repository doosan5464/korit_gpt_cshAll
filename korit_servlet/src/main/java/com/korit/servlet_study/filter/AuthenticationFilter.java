package com.korit.servlet_study.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.UserDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class AuthenticationFilter implements Filter {
    private JwtProvider jwtProvider;
    private UserDao userDao;

    public AuthenticationFilter() {
        jwtProvider = JwtProvider.getInstance();
        userDao = UserDao.getInstance();
    }

    @Override // ServletResponse, servletResponse 은 이미 업캐스팅되어있음
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // 다운캐스팅
        HttpServletResponse response = (HttpServletResponse) servletResponse; // 다운캐스팅
        // 서블릿에서 ServletRequest와 ServletResponse는 인터페이스인데, 실제로는 HttpServletRequest와 HttpServletResponse 클래스의 인스턴스가 전달돼.
        // 그래서 다운캐스팅을 해야 각각의 클래스를 사용할 수 있게 된다 (servlet 이 부모고 http가 자식)

        try {
            if(isJwtTokenValid(request)) { // 인증이 필요한 놈인가??? 라는 의미???
                String bearerToken = request.getHeader("Authorization");
                if(bearerToken == null) {
                    setUnAuthenticatedResponse(response); //
                    return;
                }

                Claims claims = jwtProvider.parseToken(bearerToken);
                if(claims == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }

                int userId = Integer.parseInt(claims.get("userId").toString());
                User foundUser = userDao.findByid(userId);
                if(foundUser == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isJwtTokenValid(HttpServletRequest request) throws ClassNotFoundException {
        String method = request.getMethod(); // doGet인지 doPost인지... 등등

        String servletPath = request.getHttpServletMapping().getServletName();
        // 요청이 매핑된 서블릿의 이름(클래스, 경로를 다 포함한)을 반환 (어느 서블렛에 요청을 했는가)

        Class<?> servletClass = Class.forName(servletPath); // Class<?> : 클래스클래스 구조.
                                                            // Class.forName(servletPath) : 이 서블릿 명의 클래스가 만들어 짐
        Method foundMethod = getMappedMethod(servletClass, method); // ???
        return foundMethod != null; // foundMethod 객체가 null이 아니면 true, 그렇지 않으면 false를 반환
    }

    private Method getMappedMethod(Class<?> clazz, String methodName) { // 서블렛클래스 clazz, 메서드 methodName
        for (Method method : clazz.getDeclaredMethods()) { // clazz.getDeclaredMethods : 해당 클래스에 정의되어 있는 메서드들
            if (method.getName().toLowerCase().endsWith(methodName.toLowerCase()) && method.isAnnotationPresent(JwtValid.class)) {
                // method 객체의 메서드 이름이 methodName으로 끝나는지 확인 (메서드랑 ~~를 소문자로)
                // 현재 메서드가 @JwtValid라는 어노테이션을 가지고 있는지 확인
                return method; // 인증이 필요한 메서드가 리턴된다???
            }
        }
        return null;
    }

    //
    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<String> responseDto = ResponseDto.forbidden("검증 할 수 없는 Access Token입니다.");
            response.setStatus(responseDto.getStatus());
            response.setContentType("application/json");
            response.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }
}