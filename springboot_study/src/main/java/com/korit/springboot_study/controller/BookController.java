package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddBookDto;
import com.korit.springboot_study.dto.request.ReqSearchBookDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "도서 정보 API")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "도서 추가")
    @PostMapping("/api/book")
    public ResponseEntity<SuccessResponseDto<Book>> addBook(@Valid @RequestBody ReqAddBookDto reqAddBookDto) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(bookService.addBook(reqAddBookDto)));
    }

    @ApiOperation(value = "도서 검색")
    @GetMapping("/api/books")
    public ResponseEntity<SuccessResponseDto<List<Book>>> searchBook(@ModelAttribute ReqSearchBookDto searchBookDto) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(bookService.getBooks(searchBookDto)));
    }


}