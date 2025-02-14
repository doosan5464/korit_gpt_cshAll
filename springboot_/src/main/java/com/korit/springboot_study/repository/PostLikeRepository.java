package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.PostLike;
import com.korit.springboot_study.mapper.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostLikeRepository {

    @Autowired
    private PostLikeMapper postLikeMapper;

    public Optional<Boolean> save(PostLike postLike) {
        return postLikeMapper.insert(postLike) < 1
                ? Optional.empty()
                : Optional.of(true);
    }
}
