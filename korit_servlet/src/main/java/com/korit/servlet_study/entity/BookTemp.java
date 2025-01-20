package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTemp {
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String category;
    private String imgUrl;
}
