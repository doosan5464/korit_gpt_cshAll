package com.korit.springboot_security.mapper;

import com.korit.springboot_security.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // Mapper 에서 타입을 바꾸면 알아서 바꿔진다?? gpt

    int insert(User user);

    User selectByUsername(String username);

    User selectById(int userId);

    List<User> selectByRoleId(int roleId);

    List<User> selectAll();

    int updateUserById(User user);

    int deleteById(int userId);
}
