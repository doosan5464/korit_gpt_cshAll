package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {
    private Key key = null;

    private static JwtProvider instance;

    private JwtProvider() {
        // jwt secret key. 지금은 학습이라 그냥 여기 올림. 원래는 static이랑 gitignore 설정
        final String SECRET = "ce88aab35c79086257e5dd67b9574f3af0c59154fa7ca2b465f008984423978d2d4b709ef074a6466a26b7fabb0e25eef3a540b09c3b2a5a549ddf0f5ff1ca087936df24a8afdabc6c8552196543527dc745a1114b346679eaab00dd6496b76d2b79d05a2ce18b6cd7b92efeaee9c8fb96cb388324e3c6f34ed0d026340d4bc1b0f2197a0d77fc00ced07a166c0dcb683ea2fe8ddc137bd97ee680afb5cd00a085ff823be770bf3ebc01a4799029aa3b8a470d31a6ae64bf7de3e45f9cf5e3d9257114cb9c6f0f806b5b49569ef028f0595d10dfa66246ea6b600562a2d1b7ca7fc7c69b98854c20a768c0bef111f74ea7882b4eff68c1ed191b241542615912";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)); // 그냥 문법
    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365)); // 1000l : 1초. 초 - 분 - 시간 - 하루 - 1년
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256) // 서명받기. 만들때 256으로 만들어서
                .compact(); // 이러면 토큰 생성
    }

    public Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder() // Jwts에서 parser(er) 변환을 해주는 Builder로 만듦
                    .setSigningKey(key) // 서명을 할 수 있게끔
                    .build() // parser를 만듦
                    .parseClaimsJws(removeBearer(token)) // token에서 claims들을 가져옴
                    .getBody(); // claim객체로 만들어줌
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
    private String removeBearer(String bearerToken) { // token앞에 bearer를 떼야 함
        String accessToken = null;
        final String BEARER_KEYWORD = "Bearer "; // 공백까지 길이 7임
        if (bearerToken.startsWith(BEARER_KEYWORD)) { // Bearer로 시작하면~ 문자열 자르기
            accessToken = bearerToken.substring(BEARER_KEYWORD.length()); // 7번 인덱스부터 ~
        }
        return accessToken;
    }

}
