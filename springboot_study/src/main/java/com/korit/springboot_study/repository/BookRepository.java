package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private BookMapper bookMapper;

    public Optional<Book> save(Book book) {
        try {
            bookMapper.insert(book);
        }catch (DuplicateKeyException e){
            return Optional.empty();
        }
        return Optional.of(book);
    }

    public Optional<List<Book>> findAllByNameContaining(String bookName) {
        System.out.println(bookName);
        return bookMapper.selectAllByNameContaining(bookName).isEmpty()
                ? Optional.empty()
                : Optional.of(bookMapper.selectAllByNameContaining(bookName));
    }
}