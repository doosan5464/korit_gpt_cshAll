package com.korit.springboot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private int postId;
    private int userId;
    private String title;
    private String content;
//    private LocalDateTime createAt;
//    private LocalDateTime updateAt;
}
