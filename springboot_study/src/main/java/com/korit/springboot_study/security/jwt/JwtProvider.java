package com.korit.springboot_study.security.jwt;

import com.korit.springboot_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
// if문 조건이 길어지는거 싫어서 만든 조건 클래스. true false 반환
public class JwtProvider {

    private Key key; //  JWT 서명, 암호화된 데이터를 보호하는 데 사용되는 키, 없으면 Parsing 못함

    public JwtProvider(@Value("${jwt.secret}") String secret) { // 생성자니까 이 클래스 만들어질떄 yml에서 설정한 암호를 @Value에 넣겠다
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));   // 그냥 기본 공식
    }

    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365));
    }
    // JWT 토큰을 여기서 생성@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public String createAccessToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("roles", user.getUserRoles().stream().map(userRole -> userRole.getRole().getRoleName()).collect(Collectors.toList()))
                .setExpiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateToken(String token) { // 토큰 유효 검증
        return token != null && token.startsWith("Bearer ");
    }

    public Claims parseToken(String token) { // parsing을 해주는 parser에게 key를 주는 클래스
        Claims claims = null; // parsing한 토큰을 담을 Claims 객체
        try {
            JwtParser parser = Jwts.parserBuilder() // parser 생성
                    .setSigningKey(key)
                    .build();
            claims = parser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    // Authorization -> AccessToken(Bearer ?????.?????.?????)
    public String removeBearer(String bearerToken) { // 앞에 붙은 Bearer를 떼주는 작업
        String token = null;
        final String BEARER_KEYWORD = "Bearer "; // Bearer는 명시적으로 jwt 토큰인걸 알려주기 위해서만 있기때문에 없앰
        if (bearerToken.startsWith(BEARER_KEYWORD)) {
            token = bearerToken.substring(BEARER_KEYWORD.length());
        }
        return token;
    }
}