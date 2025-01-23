package com.korit.servlet_study.servlet.Hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/board")
public class BoardRestServlet extends HttpServlet {
    private BoardService boardService;

    public BoardRestServlet() {
        boardService = BoardService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();

        // try-with-resources 문법 : BufferedReader 객체는 자동으로 닫힘. 객체 자동 소멸
        // BufferedReader 를 통해 요청 본문을 한 줄씩 읽음
        // getReader() : HttpServletRequest의 본문 데이터를 읽을 수 있는 문자 기반의 스트림 전체를 반환
        try (BufferedReader bufferedReader = req.getReader()) {
        // 내부적으로 HttpServletRequest 객체는 클라이언트가 보낸 요청 데이터를 바이트 기반 스트림(InputStream)으로 받음
        // getReader()는 이 바이트 스트림을 문자 스트림으로 변환하기 위해 내부적으로 InputStreamReader 를 사용
        // InputStreamReader는 문자 단위로 데이터를 읽고, BufferedReader는 이를 버퍼링하여 한 줄 단위로 읽는 기능을 제공

            String Line;
            while ((Line = bufferedReader.readLine()) != null) {
            // readLine()은 한 줄씩 읽고, read()는 한 문자씩 읽음.
            // getReader()는 전체 스트림을 반환하므로 while문을 통해 전체 데이터를 읽어들임.
                sb.append(Line);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        // JSON 데이터를 Java 객체로 변환하거나 그 반대로 변환할 때 사용 (Jackson 라이브러리 설치한거)
        InsertBoardDto insertBoardDto = mapper.readValue(sb.toString(), InsertBoardDto.class);
        // mapper.readValue(,) : JSON 문자열을 InsertBoardDto 객체로 변환하는 메서드
        // 첫번째 인자 -> 변환 대상(JSON 이어야 함). sb.toString() 은 현재 JSON 이 들어와있어서 가능홤
        //             -> VSCode 에서 axios.post(웹 주소)로 JSON 형식으로 보내주기 때문에 JSON임
        // 두번째 인자 -> InsertBoardDto.class 는 변환할 대상의 클래스 타입을 InsertBoardDto 으로 반환

        ResponseDto<?> responseDto = boardService.insertBoard(insertBoardDto);
        // BoardService의 insertBoard를 호출하여 안에 Dao의 save도 호출하게 되어 게시글을 삽입하고 그 결과를 ResponseDto의 성공, 실패로 받음

        String responseJson = mapper.writeValueAsString(responseDto);
        // ResponseDto 객체를 JSON 형식의 문자열로 변환하여 클라이언트에게 전송하기 위한 준비를 함.

        resp.setStatus(responseDto.getStatus());
        // ResponseDto의 상태 코드를 가져와 HTTP 응답 상태 코드로 설정함.

        resp.setContentType("application/json");
        // 응답의 Content-Type을 JSON 형식으로 설정함.

        resp.getWriter().write(responseJson);
        // 변환된 JSON 문자열을 HTTP 응답 본문에 작성하여 클라이언트에게 전송함.
    }
}