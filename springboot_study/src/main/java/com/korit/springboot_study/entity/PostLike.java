package com.korit.springboot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {
    private int postLikeId;
    private int postId;
    private int userId;
    private LocalDateTime createdAt;

    // 개별적으로 게시글별로 좋아요 수 가져올 떄
    private int likeCount;

}
