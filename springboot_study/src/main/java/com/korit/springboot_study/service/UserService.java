package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddUserDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.entity.UserRole;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional(rollbackFor = Exception.class)
    public User addUser(ReqAddUserDto reqAddUserDto) {
        User saveUser = userRepository
                .save(reqAddUserDto.toUser())
                .orElseThrow(
                        () -> new CustomDuplicateKeyException("", Map.of("username", "이미 사용중인 사용자이름입니다."))
                );
        userRoleRepository.save(UserRole.builder()
                .userId(saveUser.getUserId())
                .roleId(1) // RoleId(1) == ROLE_USER, ''(2) == ROLE_ADMIN, ''(3) == ROLE_MANAGER
                .build())
                .orElseThrow(() -> new RuntimeException("SQL ERROR"));
        return saveUser;
    }

    public Boolean duplicateUsername(String username) { // 딱히 error없음
        return userRepository.findByUsername(username).isPresent(); // isPresent() : 값이 있으면 true
    }
}
