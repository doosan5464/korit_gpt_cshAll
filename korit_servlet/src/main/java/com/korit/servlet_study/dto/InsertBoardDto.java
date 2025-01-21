package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertBoardDto {
    // DTO는 데이터를 전송하기 위한 객체, JSON으로 넘어온 데이터를 자바객체(DTO)로
    private String title;
    private String content;

    // Board로 바꿔서(Entity로 변환) return해줌
    // 기능 자체는 여기있지만 호출은 BoardService에서
    public Board toBoard() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
