package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.mapper.TestBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TestBookRepository {

    @Autowired
    private TestBookMapper testBookMapper;

    public Optional<List<Book>> findBooksAll(Book book) {
        List<Book> selectedBooks = testBookMapper.selectBooksAll(book);

        return selectedBooks.isEmpty()
                ? Optional.empty()
                : Optional.of(selectedBooks);
    }
}
