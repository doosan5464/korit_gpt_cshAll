package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    int insert(Book book);

    // XML의 #{bookName}과 매칭, SQL 실행 시 자동으로 값이 바인딩
    List<Book> selectAllByNameContaining(@Param(value = "bookName") String bookName);
}