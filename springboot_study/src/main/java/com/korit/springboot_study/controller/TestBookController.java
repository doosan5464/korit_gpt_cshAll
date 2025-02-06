package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.service.TestBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class TestBookController {

    @Autowired
    private TestBookService testBookService;

    @GetMapping("/api/book/{bookId}/{bookName}")
    @ApiOperation(value = "Book 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Book>>> getBooks(
    @PathVariable int bookId,
    @PathVariable String bookName
    ) {
        return ResponseEntity.ok().body(testBookService.allBooks(bookId, bookName));
    }




}
