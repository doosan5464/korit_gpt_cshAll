package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int insert(Category category);
    List<Category> selectAllByNameContaining(@Param(value = "categoryName") String categoryName);
}