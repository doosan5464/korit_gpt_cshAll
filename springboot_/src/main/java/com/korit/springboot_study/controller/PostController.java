package com.korit.springboot_study.controller;

//import com.korit.springboot_study.aspect.anntotation.TimeAop;
import com.korit.springboot_study.aspect.anntotation.TimeAop;
import com.korit.springboot_study.dto.request.ReqCreatePostDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.service.PostService;
import io.swagger.annotations.Api;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Api(tags = "게시글 API")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("api/post") // Create
    public ResponseEntity<SuccessResponseDto<Post>> createPost(@RequestBody ReqCreatePostDto reqCreatePostDto) {
        return ResponseEntity
                .created(URI.create(""))
                .body(new SuccessResponseDto<>(postService.createPost(reqCreatePostDto))); // created() -> link를 줄 때 ok()대신 쓰기도 함
    } // SuccesRequstDto 쓰다가 이렇게 쓴 이유 : 응답 코드를 body안에 넣어주고 싶어서??

    @TimeAop
    @GetMapping("api/post/{postId}") // Read. 단건
    public ResponseEntity<SuccessResponseDto<Post>> getPost(@PathVariable int postId) throws Exception {
        return ResponseEntity
                .ok()
                .body(new SuccessResponseDto<>(postService.getPostById(postId)));
    }

    @GetMapping("api/posts") // Read. 다건
    public ResponseEntity<SuccessResponseDto<List<Post>>> getPosts( // RequestParam 과 RequestBody의 차이 gpt
            @RequestParam(required = true) int page, // 페이지별로 구분
            @RequestParam(required = true) int size, // 페이지별 크기
            @RequestParam(required = false, defaultValue = "") String keyword // 없으면 전체조회?
            // 보통 Dto로 매개변수를 처리하나 3개밖에 없으면 그냥 명시적으로도 이게 나음
            // defaultValue = "" -> 빈 문자열로 가야 검색할 떄 의도대로 되고 안그러면 NULL이 검색창에 들어갈지도 모름
    ) throws Exception {
        return ResponseEntity
                .ok()
                .body(new SuccessResponseDto<>(postService.getAllPostsByKeywordContaining(page, size, keyword)));
    }

    @PostMapping("api/post/{postId}/like")
    public ResponseEntity<SuccessResponseDto<Boolean>> likePost(@PathVariable int postId) throws Exception {
        int userId = 2; // 토큰이 있다는 가정하에
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.likePost(postId, userId)));
    }

    @DeleteMapping("api/post/{postId}/dislike")
    public ResponseEntity<SuccessResponseDto<Post>> dislikePost(@PathVariable int postId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }

}