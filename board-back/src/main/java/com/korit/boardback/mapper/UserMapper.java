package com.korit.boardback.mapper;

import com.korit.boardback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectById(int userId);

    User selectByUsername(String username);

    int insert(User user);

    int updateProfileImgById(
            @Param("userId") int userId, // userId라는 파라미터를 SQL에서 사용할 수 있도록 설정
            @Param("profileImg") String profileImg); // rofileImg라는 파라미터를 SQL에서 사용할 수 있도록 설정
}