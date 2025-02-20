package com.korit.springboot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisTokenService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Redis 저장소
    // 여기에 캐시 저장

    public void setRefresh(String username, String token, Duration duration) {
        redisTemplate.opsForValue().set("refresh:" + username, token, duration);
    }

    public void setAccess(String username, String token, Duration duration) {
        redisTemplate.opsForValue().set("access:" + username, token, duration);
    }

    public String getRefresh(String username) {
        return (String) redisTemplate.opsForValue().get("refresh:" + username); // .toString() 대신 그냥 다운캐스팅 한 이유???
    }
}
