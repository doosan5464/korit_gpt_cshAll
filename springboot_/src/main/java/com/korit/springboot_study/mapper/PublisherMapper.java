package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Publisher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublisherMapper {
    int insert(Publisher publisher);
    List<Publisher> selectAllByNameContaining(@Param(value = "publisherName") String publisherName);
}