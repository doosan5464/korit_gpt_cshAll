package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.InsertBoardDto;
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


    public void insertBoard(InsertBoardDto dto) {
        // InsertBoardDto에 Builder있음
        Board board = dto.toBoard();
        // db에 저장
        boardDao.save(board);
    }
}