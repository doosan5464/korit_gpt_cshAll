package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestBookMapper {

    List<Book> selectBooksAll(Book book);
}
