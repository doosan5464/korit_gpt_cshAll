package com.korit.boardback.controller;

import com.korit.boardback.dto.request.ReqJoinDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody ReqJoinDto dto) {
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "로그인", description = "로그인 설명")
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }
}
