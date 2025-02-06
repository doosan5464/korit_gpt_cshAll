package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.repository.TestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestBookService {

    @Autowired
    private TestBookRepository testBookRepository;


    public SuccessResponseDto<List<Book>> allBooks(int bookId, String bookName) {
        List<Book> foundedBooks = testBookRepository.findBooksAll(new Book(bookId, bookName))
                .get();
        return new SuccessResponseDto<>(foundedBooks);
    }

}
