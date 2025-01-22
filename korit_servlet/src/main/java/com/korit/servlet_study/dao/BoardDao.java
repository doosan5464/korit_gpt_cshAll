package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class BoardDao {
    // db나 다른 저장소와의 상호작용을 담당하는 객체

    // 싱글톤에서 얻을 DB 연결 객체
    private DBConnectionMgr dbConnectionMgr;

    // 싱글톤
    private static BoardDao instance;
    // BoardDao의 생성자로 DBConnectionMgr의 싱글톤 getInstance를 가져옴. 안귀찮으려고?
    private BoardDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();
    }
    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }


    public Board save(Board board) {
        Board insertedBoard = null; // ??
        Connection con = null; // db 연결 관리
        PreparedStatement ps = null; // 쿼리 실행 결과 저장할 곳 // 빈 쿼리창
        ResultSet rs = null; // 쿼리 실행 결과를 저장
        // ResultSet은 빈 테이블형태, ResultSet은 기본적으로 하나의 행에 생성된 키 값이 포함
        // Set 형태

        try {
            con = dbConnectionMgr.getConnection();
            // db 연결 객체. catch필수. db와 연결되는 user,password~url까지 틀리면 예외니까

            String sql = """
                    INSERT INTO board_tb
                    VALUES (default, ?, ?)
                    """;

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Connection에 PreparedStatement(sql)을 설정 후 초기화, 삽입 후 자동 생성된 키를 가져오기 위해 RETURN_GENERATED_KEYS 사용

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            // setString() : ? 에 대입. 1개 뿐이니 1 에서 끝

            ps.executeUpdate();
            // SQL 쿼리를 실행하고, 데이터베이스의 데이터를 삽입, 업데이트, 삭제
            // execute() : 쿼리 실행 - true(select만 true, 나머지는 false), false
            // executeQuerry() : SELECT 쿼리를 실행, ResultSet을 반환
            // executeUpdate() : 데이터베이스 변경 작업 (INSERT, UPDATE, DELETE)을 실행, SQL 실행 후 영향을 받은 행(row)의 개수를 int로 반환. int 반환

            rs = ps.getGeneratedKeys();
            // PreparedStatement ps 객체에서 자동 생성된 키(AutoIncrement Key)를 조회
            if (rs.next()) {
            // ResultSet 테이블에서 커서를 첫번째 행으로 이동시키고 있는지 확인
                // INSERT 쿼리 후에 자동 생성된 키를 가져오기 위함
                // 딱히 위에서 뭔갈 하고있지는 않음
                insertedBoard = Board.builder()
                        .board_id(rs.getInt(1)) // ResultSet의 첫번째 칼럼의 값을 가져온다
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // RuntimeException : 실행해봐야 아는 에러
        } finally {
            dbConnectionMgr.freeConnection(con, ps); // 연결된 데이터베이스 리소스를 해제
        }
        return insertedBoard;
    }
}