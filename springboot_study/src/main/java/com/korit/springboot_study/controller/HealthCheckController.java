package com.korit.springboot_study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
// 헬스 체크(Health Check)는 서버나 애플리케이션의 상태를 확인하는 방법
@RestController
public class HealthCheckController {

    @GetMapping("/server/hc")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}