package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddPostDto;
import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.PostRepository;
import com.korit.springboot_study.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public Post addPost(ReqAddPostDto reqAddPostDto) {
        return postRepository.save(
                reqAddPostDto.toPost()
        ).orElseThrow(() -> new CustomDuplicateKeyException("이미이"));
    }

    public Post getById(int userId) throws NotFoundException {
        return postRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다"));
    }

    public List<Post> getAllPost() throws NotFoundException {
        return postRepository.findAll()
                .orElseThrow(() -> new NotFoundException("게시글 정보가 존재하지 않습니다."));
    }
}
