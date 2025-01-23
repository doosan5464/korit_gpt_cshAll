/** @jsxImportSource @emotion/react */
import axios from 'axios';
import * as s from './style';
import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill'; // Rich text editor 라이브러리 (Quill) 사용

function WritePage(props) {
    // Quill 에디터의 툴바 옵션 정의
    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'], // 텍스트 스타일
        ['blockquote', 'code-block'], // 블록 스타일
        ['link', 'image', 'video', 'formula'], // 미디어 삽입
        [{ 'header': 1 }, { 'header': 2 }], // 헤더 크기
        [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }], // 목록
        [{ 'script': 'sub'}, { 'script': 'super' }], // 위/아래 첨자
        [{ 'indent': '-1'}, { 'indent': '+1' }], // 들여쓰기
        [{ 'direction': 'rtl' }], // 텍스트 방향
        [{ 'size': ['small', false, 'large', 'huge'] }], // 텍스트 크기
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }], // 헤더 수준
        [{ 'color': [] }, { 'background': [] }], // 텍스트 및 배경 색상
        [{ 'font': [] }], // 폰트 종류
        [{ 'align': [] }], // 정렬
        ['clean'] // 모든 포맷 제거
    ];

    useEffect(() => { 
        // Quill 에디터의 스타일시트를 추가하여 기본 스타일 적용
        const head = document.querySelector("head");
        const link = document.createElement("link");
        link.rel = "stylesheet";
        link.href = "https://unpkg.com/react-quill@1.3.3/dist/quill.snow.css";
        head.appendChild(link);
    }, []);

    // 입력값을 관리하는 상태
    const [inputValue, setInputValue] = useState({
        title: "", // 게시글 제목
        content: "", // 게시글 내용
    });

    // 제목 입력값 변경 핸들러
    const handleInputOnChange = (e) => {
        setInputValue({
            ...inputValue,
            [e.target.name]: e.target.value,
        });
    };

    // Quill 에디터의 내용 변경 핸들러 (value는 에디터 내용)
    const handleQuillOnChange = (value) => {
        setInputValue({
            ...inputValue,
            content: value,
        });
    };

    // 작성 버튼 클릭 시 API 호출
    const handleWriteSubmitOnClick = async () => {
        try { 
            // 서버에 게시글 데이터를 전송 (axios가 객체를 자동으로 JSON 형식으로 변환)
            const response = await axios.post(
                "http://localhost:8080/servlet_study_war/api/board", 
                inputValue
            ); 
            console.log(response); // 성공 응답 확인
            alert("게시글 작성 완료");
        } catch (error) {
            console.error(error); // 에러 처리
        }
    };

    return (
        <div>
            {/* 상단 작성 버튼 */}
            <div css={s.headerLayout}>
                <button onClick={handleWriteSubmitOnClick}>작성하기</button>
            </div>
            {/* 제목 입력 필드 */}
            <div css={s.titleLayout}>
                <input 
                    type="text" 
                    placeholder="제목을 입력하세요." 
                    name="title" 
                    value={inputValue.title} 
                    onChange={handleInputOnChange} 
                />
            </div>
            {/* Quill 에디터 */}
            <ReactQuill 
                modules={{
                    toolbar: toolbarOptions, // 사용자 정의 툴바 옵션
                }}
                style={{
                    boxSizing: "border-box",
                    width: "100%",
                    height: "600px", // 에디터 높이 설정
                }}
                value={inputValue.content} // 에디터의 내용 상태와 동기화
                onChange={handleQuillOnChange} // 변경 이벤트 핸들러
            />
        </div>
    );
}

export default WritePage;
