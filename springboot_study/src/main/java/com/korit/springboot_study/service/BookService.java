package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddBookDto;
import com.korit.springboot_study.dto.request.ReqSearchBookDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.BookRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(ReqAddBookDto reqAddBookDto) {
        return bookRepository
                .save(reqAddBookDto.toBook())
                .orElseThrow(() -> new CustomDuplicateKeyException("이미 존재하는 도서명입니다."));
    }

    public List<Book> getBooks(ReqSearchBookDto reqSearchBookDto) throws Exception {
        return bookRepository.findAllByNameContaining(reqSearchBookDto.getKeyword())
                .orElseThrow(() -> new NotFoundException("조회된 도서가 없습니다."));
    }
}