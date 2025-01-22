package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.Board;

public class BoardService {
    // DTO에서 service 인스턴스를 얻으면 이 service 인스턴스로 insertBoard함수 호출
    // 즉, DTO를 Service가 받아서 DAO에 전달

    private static BoardService instance;

    public static BoardService getInstance() {
        if (instance == null) {
            instance = new BoardService();
        }
        return instance;
    }


    private BoardDao boardDao;

    // 생성자로 BoardService 인스턴스 얻자마자 Dao와 연결
    private BoardService() {
        boardDao = BoardDao.getInstance();
    }


    // return이 타입이 달라서 <?> 와일드카드로 줌
    public ResponseDto<?> insertBoard(InsertBoardDto dto) {
        // InsertBoardDto에서 Book타입의 Entity로 변환
        Board board = dto.toBoard();

        Board insertBoard = boardDao.save(board);
        if(insertBoard == null) {
            return ResponseDto.fail("게시글 작성 실패!");
        }
        return ResponseDto.success(insertBoard);
    }
}