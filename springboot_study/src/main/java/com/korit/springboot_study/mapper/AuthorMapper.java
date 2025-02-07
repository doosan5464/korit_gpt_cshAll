package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorMapper {
    int insert(Author author);
    List<Author> selectAllByNameContaining(@Param(value = "authorName") String authorName);
}