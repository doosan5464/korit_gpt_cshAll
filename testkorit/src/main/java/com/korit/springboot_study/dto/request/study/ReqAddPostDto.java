package com.korit.springboot_study.dto.request.study;

import com.korit.springboot_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReqAddPostDto {
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
