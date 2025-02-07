package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddAuthorDto;
import com.korit.springboot_study.dto.request.ReqSearchAuthorDto;
import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.repository.AuthorRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(ReqAddAuthorDto reqAddAuthorDto) {
        return authorRepository
                .save(reqAddAuthorDto.toAuthor())
                .get();
    }

    public List<Author> getAuthors(ReqSearchAuthorDto reqSearchAuthorDto) throws Exception {
        return authorRepository.findAllByNameContaining(reqSearchAuthorDto.getKeyword())
                .orElseThrow(() -> new NotFoundException("조회된 저자가 없습니다."));
    }
}