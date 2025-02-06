package com.korit.springboot_study.dto.request;

import lombok.Data;

@Data
public class ReqSelectBookDto {
    private int bookId;
    private String bookName;
}
