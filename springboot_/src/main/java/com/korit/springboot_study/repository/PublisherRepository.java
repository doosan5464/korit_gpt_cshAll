package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublisherRepository {

    @Autowired
    private PublisherMapper publisherMapper;

    public Optional<Publisher> save(Publisher publisher) {
        try {
            publisherMapper.insert(publisher);
        }catch (DuplicateKeyException e){
            throw new CustomDuplicateKeyException("이미 존재하는 출판사명입니다.");
        }
        return Optional.of(publisher);
    }

    public Optional<List<Publisher>> findAllByNameContaining(String publisherName) {
        System.out.println(publisherName);
        return publisherMapper.selectAllByNameContaining(publisherName).isEmpty()
                ? Optional.empty()
                : Optional.of(publisherMapper.selectAllByNameContaining(publisherName));
    }
}