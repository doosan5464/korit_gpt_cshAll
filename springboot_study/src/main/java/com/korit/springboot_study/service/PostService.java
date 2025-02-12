package com.korit.springboot_study.service;

import com.korit.springboot_study.aspect.anntotation.PrintParamsAop;
import com.korit.springboot_study.dto.request.ReqCreatePostDto;
import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.entity.PostLike;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.PostLikeRepository;
import com.korit.springboot_study.repository.PostRepository;
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
    private PostLikeRepository postLikeRepository;

    @Transactional(rollbackFor = Exception.class) // C U D 는 @Transactional 거의 필수!
    public Post createPost(ReqCreatePostDto reqCreatePostDto) {
        return postRepository.save(reqCreatePostDto.toPost())
                .get(); // db에러는 딱히 예외처리 해줄 그게 없다??
    }

    @PrintParamsAop //
    public Post getPostById(int postId) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("해당 postId의 게시글이 존재하지 않습니다."));
        return post;
    }

    @PrintParamsAop //
    public List<Post> getAllPostsByKeywordContaining(int page, int size, String keyword) throws Exception {
        int startIndex = (page - 1) * size; // 인덱스는 0부터니까, page는 1부터 시작하니까
        List<Post> posts = postRepository.findAllByKeywordContaining(startIndex, size, keyword)
                .orElseThrow(() -> new NotFoundException("검색된 정보가 없습니다;"));
        return posts;
    }

    public Boolean likePost(int postId, int userId) throws Exception {
        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
        return postLikeRepository.save(postLike)
                .orElseThrow(() -> new CustomDuplicateKeyException("", Map.of("message", "해당 게시글을 이미 like 처리하였습니다.")));

    }

}
