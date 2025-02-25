package com.korit.boardback.mapper;

import com.korit.boardback.entity.User;
import com.korit.boardback.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole userRole);
}