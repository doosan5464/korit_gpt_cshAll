package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int insert (Post post);


    Post selectByPostId(int postId);


    List<Post> selectAllPost();


    int insertLike(int postId, int userId);
    int deleteByPostId(int postId, int userId);
    Post selectLikeByPostId(int postId);
}
