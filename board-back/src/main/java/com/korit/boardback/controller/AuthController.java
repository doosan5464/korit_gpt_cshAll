package com.korit.boardback.controller;

import com.korit.boardback.dto.request.ReqJoinDto;
import com.korit.boardback.dto.request.ReqLoginDto;
import com.korit.boardback.dto.response.RespTokenDto;
import com.korit.boardback.entity.User;
import com.korit.boardback.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody ReqJoinDto dto) {
        return ResponseEntity.ok().body(userService.join(dto));
    }


    @Operation(summary = "로그인", description = "로그인 설명")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqLoginDto dto) {
        /*
        UserService -> login()
        User객체 findByUsername
        user가 있으면 비밀번호 일치하는지 확인
        비밀번호가 일치하면 JWT응답
        JwtUtil -> secret 세팅
        */
        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("Bearer")
                .name("AccessToken")
                .token(userService.login(dto))
                .build();

        return ResponseEntity.ok().body(respTokenDto);
    }
}