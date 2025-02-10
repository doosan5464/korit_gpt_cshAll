package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);

    User selectByUserId(int userId);
}
