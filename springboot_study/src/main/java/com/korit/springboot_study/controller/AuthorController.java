package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddAuthorDto;
import com.korit.springboot_study.dto.request.ReqSearchAuthorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "저자 정보 API")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "도서 저자 추가")
    @PostMapping("/api/book/author")
    public ResponseEntity<SuccessResponseDto<Author>> addAuthor(@Valid @RequestBody ReqAddAuthorDto reqAddAuthorDto) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authorService.addAuthor(reqAddAuthorDto)));
    }

    @ApiOperation(value = "도서 저자 검색")
    @GetMapping("/api/book/authors")
    public ResponseEntity<SuccessResponseDto<List<Author>>> searchAuthor(@ModelAttribute ReqSearchAuthorDto searchAuthorDto) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authorService.getAuthors(searchAuthorDto)));
    }


}