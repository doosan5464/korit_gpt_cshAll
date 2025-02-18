package com.korit.springboot_security.controller;

import com.korit.springboot_security.dto.request.auth.ReqSigninDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // 모든 요청 url 앞에 공통 사항
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody ReqSigninDto reqSigninDto) {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(null);
    }
}
