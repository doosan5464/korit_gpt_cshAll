package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddCategoryDto;
import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.repository.CategoryRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(ReqAddCategoryDto reqAddCategoryDto) {
        return categoryRepository
                .save(reqAddCategoryDto.toCategory())
                .get();
    }

    public List<Category> getCategories(String keyword) throws Exception {
        return categoryRepository.findAllByNameContaining(keyword)
                .orElseThrow(() -> new NotFoundException("조회된 카테고리가 없습니다."));
    }
}