package com.korit.springboot_security.repository;

import com.korit.springboot_security.entity.UserRole;
import com.korit.springboot_security.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public Optional<UserRole> save(UserRole userRole) {
        return userRoleMapper.insert(userRole) < 1
                ? Optional.empty()
                : Optional.of(userRole);
    }

    public Optional<Boolean> saveSelective(int userId, String roleName) {
        return Optional.of(userRoleMapper.insertSelective(userId, roleName) < 1);
    }
}
