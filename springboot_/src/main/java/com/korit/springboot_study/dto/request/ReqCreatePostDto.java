package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Post;
import lombok.Data;

@Data
public class ReqCreatePostDto {
    private int userId;
    private String title;
    private String content;

    public Post toPost() {
        return Post.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
