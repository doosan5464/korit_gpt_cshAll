package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SigninDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.jwt.JwtProvider;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private AuthDao authDao; // 회원가입, 로그인 관련 Dao
    private JwtProvider jwtProvider; //

    private static AuthService instance;

    private AuthService() {
        authDao = AuthDao.getInstance();
        jwtProvider = JwtProvider.getInstance();
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public ResponseDto<?> signup(SignupDto signupDto) {
        User insertedUser = authDao.signup(signupDto.toUser()); // Dao를 이용해
        if(insertedUser == null) {
            return ResponseDto.fail("사용자를 추가하지 못하였습니다.");
        }
        return ResponseDto.success(insertedUser);
    }

    public ResponseDto<?> signin(SigninDto signinDto) {
        User foundUser = authDao.findUserByUsername(signinDto.getUsername());
        if(foundUser == null) {
            return ResponseDto.fail("사용자 정보를 다시 확인하세요.");
        }
        // BCrypt : 비밀번호 암호화를 위한 라이브러리
        // 암호화된 비밀번호는 직접 비교할 수 없기 때문에, BCrypt가 내부적으로 암호화 알고리즘을 사용해 두 값을 비교
        if(!BCrypt.checkpw(signinDto.getPassword(), foundUser.getPassword())) {
            return ResponseDto.fail("사용자 정보를 다시 확인하세요.");
        }
        return ResponseDto.success(jwtProvider.generateToken(foundUser)); // 일치한다면 토큰 생성
    }
}