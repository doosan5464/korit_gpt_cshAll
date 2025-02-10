package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqAddPostDto;
import com.korit.springboot_study.dto.request.study.ReqAddUserDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@Api(tags = "사용자 정보 관리")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto<?>> addPost(@Valid @RequestBody ReqAddPostDto reqAddPostDto) throws MethodArgumentNotValidException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.addPost(reqAddPostDto)));
    }

    @GetMapping("/api/post/{postId}")
    @ApiOperation(value = "Post ID로 조회")
    public ResponseEntity<SuccessResponseDto<Post>> getPost(
            @Min(value = 1, message = "Post ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int postId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getById(postId)));
    }

    @GetMapping("/api/posts")
    @ApiOperation(value = "게시글 정보 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Post>>> getPosts() throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getAllPost()));
    }

//    @PostMapping("/api/post/{postId}/like/{userId}")
//
//    @DeleteMapping("/api/post/{postId}/like/{userId}")
//
//    @GetMapping("/api/post/{postId}/like/count")







}
