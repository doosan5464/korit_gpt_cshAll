package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddPublisherDto;
import com.korit.springboot_study.dto.request.ReqSearchPublisherDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.service.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "출판사 정보 API")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @ApiOperation(value = "도서 출판사 추가")
    @PostMapping("/api/book/publisher")
    public ResponseEntity<SuccessResponseDto<Publisher>> addPublisher(@Valid @RequestBody ReqAddPublisherDto reqAddPublisherDto) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(publisherService.addPublisher(reqAddPublisherDto)));
    }

    @ApiOperation(value = "도서 출판사 검색")
    @GetMapping("/api/book/publishers")
    public ResponseEntity<SuccessResponseDto<List<Publisher>>> searchPublisher(@ModelAttribute ReqSearchPublisherDto searchPublisherDto) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(publisherService.getPublishers(searchPublisherDto)));
    }


}