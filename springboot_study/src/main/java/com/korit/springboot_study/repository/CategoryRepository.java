package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public Optional<Category> save(Category category) {
        try {
            categoryMapper.insert(category);
        }catch (DuplicateKeyException e){
            return Optional.empty(); // 여기 말고 Service에서 예외를 처리하기로 약속
        }
        return Optional.ofNullable(category);
    }

    public Optional<List<Category>> findAllByNameContaining(String categoryName) {
        System.out.println(categoryName);
        return categoryMapper.selectAllByNameContaining(categoryName).isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(categoryMapper.selectAllByNameContaining(categoryName));
    }
}