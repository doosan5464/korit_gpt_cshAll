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


// AuthenticationFilter는 javax.servlet.Filter 인터페이스를 구현하므로, 서블릿 컨테이너(예: Tomcat)가 필터로 인식하고, 모든 요청을 통과
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
            if(isJwtTokenValid(request)) { // 해당 요청이 JWT 인증이 필요한 요청인지 확인
                String bearerToken = request.getHeader("Authorization"); // Authorization 헤더에서 토큰 가져오기
                if(bearerToken == null) {
                    setUnAuthenticatedResponse(response); // 토큰이 없으면 인증 실패 응답 반환
                    return;
                }

                Claims claims = jwtProvider.parseToken(bearerToken); // JWT 파싱하여 Claims(정보) 가져오기
                if(claims == null) {
                    setUnAuthenticatedResponse(response); // 토큰이 유효하지 않으면 인증 실패 응답 반환
                    return;
                }

                int userId = Integer.parseInt(claims.get("userId").toString()); // 토큰에서 userId 추출
                User foundUser = userDao.findByid(userId); // DB에서 userId로 사용자 조회
                if(foundUser == null) {
                    setUnAuthenticatedResponse(response); // 유저 정보가 없으면 인증 실패 응답 반환
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
        // 요청을 처리하는 서블릿 클래스의 이름(패키지 포함)을 가져옴.

        Class<?> servletClass = Class.forName(servletPath); // Class<?> : 클래스클래스 구조.
                                                            // Class.forName(servletPath) : 이 서블릿 명의 클래스가 만들어 짐
        Method foundMethod = getMappedMethod(servletClass, method); // 해당 HTTP 메서드에 매핑된 메서드 찾기
        return foundMethod != null; // foundMethod 객체가 null이 아니면 true, 그렇지 않으면 false를 반환
                                    // @JwtValid가 붙은 메서드가 있으면 true, 없으면 false
    }

    private Method getMappedMethod(Class<?> clazz, String methodName) { // 서블렛클래스 clazz, 메서드 methodName
        for (Method method : clazz.getDeclaredMethods()) { // clazz.getDeclaredMethods : 해당 클래스에 정의되어 있는 메서드들
            if (method.getName().toLowerCase().endsWith(methodName.toLowerCase()) && method.isAnnotationPresent(JwtValid.class)) {
                // method 객체의 메서드 이름이 methodName으로 끝나는지 확인 (메서드랑 ~~를 소문자로)
                // 현재 메서드가 @JwtValid라는 어노테이션을 가지고 있는지 확인
                return method; // 해당 메서드 반환 (JWT 인증이 필요한 메서드)
            }
        }
        return null; // 인증이 필요 없는 경우 null 반환
    }

    // JWT 인증 실패 시 응답 처리
    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<String> responseDto = ResponseDto.forbidden("검증 할 수 없는 Access Token입니다.");
            response.setStatus(responseDto.getStatus()); // 403 상태 코드 설정
            response.setContentType("application/json");
            response.getWriter().println(objectMapper.writeValueAsString(responseDto)); // JSON 형식으로 응답 반환
    }
}