package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddCategoryDto;
import com.korit.springboot_study.dto.request.ReqSearchCategoryDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "카테고리 API")
public class CategoryController {
// Controller 에서는 SuccessResponseDto. 즉 성공을 기준으로 Dto로 처리함

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "도서 카테고리 추가")
    @PostMapping("/api/book/category")
    // @Valid 로 ReqAddCategoryDto의 @Pattern 과 연결
    public ResponseEntity<SuccessResponseDto<Category>> addCategory(@Valid @RequestBody ReqAddCategoryDto reqAddCategoryDto) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(categoryService.addCategory(reqAddCategoryDto)));
    }

    @ApiOperation(value = "도서 카테고리 검색")
    @GetMapping("/api/book/categories")
    public ResponseEntity<SuccessResponseDto<List<Category>>> searchCategory(@ModelAttribute ReqSearchCategoryDto searchCategoryDto) throws Exception {
    // @ModelAttribute : HTTP 요청 파라미터를 객체(DTO)로 매핑할 때 사용하는 어노테이션
    // -> Spring이 keyword 값을 ReqSearchCategoryDto 객체에 자동으로 매핑
        return ResponseEntity.ok().body(new SuccessResponseDto<>(categoryService.getCategories(searchCategoryDto)));
    }


}