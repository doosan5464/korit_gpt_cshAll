package com.korit.springboot_study.security.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Value("${oauth2.client.redirect_uri}")
    private String client_redirect_uri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // 다운캐스팅. 왜?
        String oauth2Id = oAuth2User.getAttribute("oauth2Id");
        String provider = oAuth2User.getAttribute("provider");

        response.sendRedirect(client_redirect_uri + "?oauth2Id=" + oauth2Id + "&provider=" + provider);
        // 구글 로그인창은 우리께 아님. 리다이렉트를 여기로 오는데 리액트로(구글계정, 이메일? 도같 이) 넘겨줘야 해서 이런 코드를 짬
        // 토큰도 줘야함. 그래서 무조건 Get임.
    }


}
