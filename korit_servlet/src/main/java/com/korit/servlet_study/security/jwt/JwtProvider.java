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
    private Key key = null; // JWT 서명을 위한 Key 객체

    private static JwtProvider instance;

    private JwtProvider() {
        // jwt secret key(비밀 키). 지금은 학습이라 그냥 여기 올림. 원래는 static 이랑 gitignore 설정
        final String SECRET = "ce88aab35c79086257e5dd67b9574f3af0c59154fa7ca2b465f008984423978d2d4b709ef074a6466a26b7fabb0e25eef3a540b09c3b2a5a549ddf0f5ff1ca087936df24a8afdabc6c8552196543527dc745a1114b346679eaab00dd6496b76d2b79d05a2ce18b6cd7b92efeaee9c8fb96cb388324e3c6f34ed0d026340d4bc1b0f2197a0d77fc00ced07a166c0dcb683ea2fe8ddc137bd97ee680afb5cd00a085ff823be770bf3ebc01a4799029aa3b8a470d31a6ae64bf7de3e45f9cf5e3d9257114cb9c6f0f806b5b49569ef028f0595d10dfa66246ea6b600562a2d1b7ca7fc7c69b98854c20a768c0bef111f74ea7882b4eff68c1ed191b241542615912";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)); // 그냥 문법
    }

    // 싱글톤 패턴으로 1개의 비밀키를 생성
    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    // 만료날짜 설정
    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365)); // 1000l : 1초. 초 - 분 - 시간 - 하루 - 1년
    }

    // 여기서 userid에 따라 다른 토큰 생성
    // 토큰이 없으면 인증이 필요한 요청은 모두 안되게
    public String generateToken(User user) {
        return Jwts.builder() // JWT ACCESS KEY 토큰 만들기
                .claim("userId", user.getUserId()) // 사용자 ID를 클레임에 추가
                .setExpiration(getExpireDate()) // 1년치로 만듦. getExpireDate() : 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명받기. 만들때 256으로 만들어서
                .compact(); // 최종적으로 JWT 문자열 생성. .build()가 아니라 .compact()
    }

    public Claims parseToken(String token) { // JWT Access Token 파싱 (유효성 검증 및 클레임 추출)
        Claims claims = null; // 추출한 토큰을 담을 claims 객체 (JWT의 body 부분)
        try {
            claims = Jwts.parserBuilder() // JWT 파서 빌더 생성. 파싱(Parsing) : 문자열 데이터를 특정한 규칙이나 형식에 따라 해석하고 분석하는 과정
                    .setSigningKey(key) // 서명 검증에 사용할 Key 설정
                    .build() // 파서 빌드
                    .parseClaimsJws(removeBearer(token)) // "Bearer " 문자열 제거 후 토큰 파싱
                    .getBody(); // 토큰에서 클레임(Body) 추출
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 로그 출력 (실제 환경에서는 로깅 처리)
        }
        return claims; // 클레임 반환 (예외 발생 시 null 반환)
    }

    // JWT 토큰을 검증하거나 데이터를 파싱하려면, 토큰만 꺼내서 사용해야 한다. 그래서 뗌
    private String removeBearer(String bearerToken) { // token앞에 bearer를 떼야 함
        String accessToken = null;
        final String BEARER_KEYWORD = "Bearer "; // "Bearer " 문자열 (공백 포함) 공백까지 길이 7임
        if (bearerToken.startsWith(BEARER_KEYWORD)) { // Bearer로 시작하면~ 문자열 자르기
            accessToken = bearerToken.substring(BEARER_KEYWORD.length()); // 7번 인덱스부터 ~ 자르기 ( "Bearer " 이후)
        }
        return accessToken; // 순수 Access Token 반환
    }

}