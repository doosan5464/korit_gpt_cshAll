package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    private PostMapper postMapper;


    public Optional<Post> save(Post post) {
        try {
            postMapper.insert(post);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(post);
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(postMapper.selectByPostId(id));
    }

    public Optional<List<Post>> findAll() {
//        List<Post> findAllPosts = postMapper.selectAllPost();
//
//        return findAllPosts().isEmpty()
//                ? Optional.empty()
//                : Optional.of(findAllPosts);
        return Optional.ofNullable(postMapper.selectAllPost());
    }


}
