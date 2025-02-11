package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostLikeMapper {

    int insert(PostLike postLike);

    int deleteByPostIdAndUserId(PostLike postLike);



    int selectLikeCountByPostId(@Param("postId") int postId);

    List<PostLike> selectLikeCountAll();

}
