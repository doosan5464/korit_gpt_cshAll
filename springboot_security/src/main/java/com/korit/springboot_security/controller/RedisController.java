package com.korit.springboot_security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.springboot_security.dto.request.redis.ReqRedisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController // REST API 컨트롤러로 동작하는 클래스임을 선언
@RequestMapping("/api/redis") // 해당 컨트롤러의 기본 URL 경로 설정
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Redis 작업을 위한 RedisTemplate 객체

    @Autowired
    private ObjectMapper objectMapper; // JSON 변환을 위한 ObjectMapper 객체

    /**
     * JSON 데이터를 Redis에 저장하는 API
     * @param reqRedisDto 저장할 JSON 데이터를 포함한 DTO
     * @return 성공 시 200 OK 응답
     * @throws JsonProcessingException JSON 변환 오류 발생 시 예외 처리
     */
    @PostMapping("/json")
    public ResponseEntity<String> jsonSet(@RequestBody ReqRedisDto reqRedisDto) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(reqRedisDto); // 객체를 JSON 문자열로 변환
        redisTemplate.opsForValue().set(reqRedisDto.getKey(), json, Duration.ofMinutes(5)); // Redis에 저장 (5분 동안 유지)
        return ResponseEntity.ok().build(); // 성공 응답 반환
    }

    /**
     * Redis에서 JSON 데이터를 가져오는 API
     * @param key 조회할 데이터의 키 값
     * @return 해당 키의 JSON 데이터를 변환한 객체 반환
     * @throws JsonProcessingException JSON 변환 오류 발생 시 예외 처리
     */
    @GetMapping("/json/{key}")
    public ResponseEntity<?> jsonGet(@PathVariable String key) throws JsonProcessingException {
        String value = redisTemplate.opsForValue().get(key).toString(); // Redis에서 해당 키의 값을 조회 (문자열 형태)
        ReqRedisDto reqRedisDto = objectMapper.readValue(value, ReqRedisDto.class); // JSON 문자열을 객체로 변환
        return ResponseEntity.ok(reqRedisDto); // 변환된 객체 반환
    }

    /**
     * 단순 문자열 값을 Redis에 저장하는 API
     * @param key 저장할 데이터의 키
     * @param value 저장할 문자열 값
     * @return 성공 시 200 OK 응답
     */
    @PostMapping("/{key}/{value}")
    public ResponseEntity<?> set(@PathVariable String key, @PathVariable String value) {
        // user:1:name, "테스트"
        redisTemplate.opsForValue().set("user:" + key + ":name", value, Duration.ofSeconds(60)); // Redis에 값 저장 (60초 유지)
        return ResponseEntity.ok().build(); // 성공 응답 반환
    }

    /**
     * Redis에서 문자열 값을 조회하는 API
     * @param key 조회할 데이터의 키 값
     * @return 해당 키에 저장된 문자열 값 반환
     */
    @GetMapping("/{key}")
    public ResponseEntity<?> get(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key).toString(); // Redis에서 키 값을 조회
        return ResponseEntity.ok(value); // 조회된 값 반환
    }


}
