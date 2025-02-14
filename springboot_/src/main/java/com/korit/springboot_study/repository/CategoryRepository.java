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
            throw new CustomDuplicateKeyException("이미 존재하는 카테고리명입니다.");
        }
        return Optional.of(category);
    }

    public Optional<List<Category>> findAllByNameContaining(String categoryName) {
        System.out.println(categoryName);
        return categoryMapper.selectAllByNameContaining(categoryName).isEmpty()
                ? Optional.empty()
                : Optional.of(categoryMapper.selectAllByNameContaining(categoryName));
    }
}