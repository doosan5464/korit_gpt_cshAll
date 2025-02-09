package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddCategoryDto;
import com.korit.springboot_study.dto.request.ReqSearchCategoryDto;
import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.repository.CategoryRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    // @Service 클래스에서 Dto를 매개변수로
    // 클라이언트와 서비스 계층 간 데이터를 깔끔하게 주고받기 위해 사용
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(ReqAddCategoryDto reqAddCategoryDto) {
        return categoryRepository
                .save(reqAddCategoryDto.toCategory()) // DTO → Entity 변환, insert는 save로 명칭 통일
                // toCategory() 메서드로 필요한 필드만 Category 객체로 변환 → 불필요한 데이터 보호
                .get();
    }

    public List<Category> getCategories(ReqSearchCategoryDto reqSearchCategoryDto) throws Exception {
        return categoryRepository
                .findAllByNameContaining(reqSearchCategoryDto.getKeyword()) // @Data의 Getter로 검색어 가져오기
                .orElseThrow(() -> new NotFoundException("조회된 카테고리가 없습니다."));
    }
}
