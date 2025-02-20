package com.korit.springboot_security.controller;

import com.korit.springboot_security.dto.request.auth.ReqRefreshDto;
import com.korit.springboot_security.dto.request.auth.ReqSigninDto;
import com.korit.springboot_security.service.AuthService;
import com.korit.springboot_security.service.RedisTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // 모든 요청 url 앞에 공통 사항
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private RedisTokenService redistokenService;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody ReqSigninDto reqSigninDto) {
        return ResponseEntity.ok().body(authService.login(reqSigninDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refresh(@RequestBody ReqRefreshDto reqRefreshDto) {
        return ResponseEntity.ok().body(authService.refresh(reqRefreshDto.getRefreshToken()));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(null);
    }
}