package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddUserDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User addUser(ReqAddUserDto reqAddUserDto) {
         return userRepository
                .save(reqAddUserDto.toUser())
                .get();

    }

    public User getUserById(int userId) throws NotFoundException {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다"));
    }
}
