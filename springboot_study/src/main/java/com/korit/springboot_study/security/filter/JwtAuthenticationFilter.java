package com.korit.springboot_study.security.filter;

import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.security.jwt.JwtProvider;
import com.korit.springboot_study.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
// 필터중에 1번째
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // 다운캐스팅부터 함
        // JWT 토큰은 HttpServlet에 있으니까.(유저가 입력한 경로?)
        String authorization = request.getHeader("Authorization");  // 그 다음 요청의 헤더에서 "Authorization"를 가져옴(있으면 인증받은거고 없으면 인증안받은거임)
                                                                    // "Authorization" : Bearer Token (JWT)
        if (jwtProvider.validateToken(authorization)) { //
            setJwtAuthentication(authorization);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setJwtAuthentication(String bearerToken) {
        Claims claims = jwtProvider.parseToken(bearerToken); // Claims를 꺼내봄.
        if (claims == null) { // 유효성검사를 했는데 실패한 경우
            throw new JwtException("Invalid JWT token"); // 예외에 던져버림
        }
        // 유효성 검사를 무사히 마쳤다면
        int userId = Integer.parseInt(claims.get("userId").toString()); // parsing 해줄려고, JWT에서 userId 추출, claims는 requset body니까 Object를 반환. 그래서 toString()
        userRepository.findById(userId).ifPresent(user -> { // userId로 사용자 조회
            SecurityContext securityContext = SecurityContextHolder.getContext(); // Spring Security의 Authentication 설정


            PrincipalUser principalUser = new PrincipalUser(user); // PrincipalUser에 user를 넣어서 db에서 정보들과 권한들을 받음
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser,principalUser.getPassword(), principalUser.getAuthorities());
            // 권한 ~~~

            securityContext.setAuthentication(authentication);
        });
    }
}
