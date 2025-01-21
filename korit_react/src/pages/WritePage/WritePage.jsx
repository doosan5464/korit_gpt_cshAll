/**@jsxImportSource @emotion/react */
import axios from 'axios';
import * as s from './style';
import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill'; // 외부 라이브러리. 게시판 검색 관련 기능을 제공해줌. 사이트에 어떻게 설정하는지 올라와있음


function WritePage(props) {

    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        ['link', 'image', 'video', 'formula'],
        
        [{ 'header': 1 }, { 'header': 2 }],
        [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
        [{ 'script': 'sub'}, { 'script': 'super' }],
        [{ 'indent': '-1'}, { 'indent': '+1' }], 
        [{ 'direction': 'rtl' }],
        
        [{ 'size': ['small', false, 'large', 'huge'] }],
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
        
        [{ 'color': [] }, { 'background': [] }], 
        [{ 'font': [] }],
        [{ 'align': [] }],
        
        ['clean'] 
    ];

    useEffect(() => { // 스타일시트를 동적으로 추가해 Quill 에디터의 기본 스타일링을 가져옴
        const head = document.querySelector("head");
        const link = document.createElement("link");
        link.rel = "stylesheet";
        link.href = "https://unpkg.com/react-quill@1.3.3/dist/quill.snow.css";
        head.appendChild(link);
    }, []);

    const [ inputValue, setInputValue ] = useState({
        title: "",
        content: "",
    });

    const handleInputOnChange = (e) => {
        setInputValue({
            ...inputValue,
            [e.target.name]: e.target.value,
        });
    }

    // Quill은 e가 아니라 value를 바로 들고옴
    const handleQuillOnChange = (value) => {
        setInputValue({
            ...inputValue,
            content: value,
        })
    }

    const handleWriteSubmitOnClick = async () => {
        
        try { // 백엔드 서버에 데이터 주겠다, inputValue는 객체지만 axios는 post요청때 자동으로 JSON 변환을 해준다
            const response = await axios.post("http://localhost:8080/servlet_study_war/api/board", inputValue); 
        } catch (error) {
            
        }
    }
    

    return (
        <div>
            <div css={s.headerLayout}>
                <button onClick={handleWriteSubmitOnClick}>작성하기</button>
            </div>
            <div css={s.titleLayout}>
                <input type="text" placeholder='제목을 입력하세요.' name='title' value={inputValue.title} onChange={handleInputOnChange}/>
            </div>
            <ReactQuill 
                modules={{
                    toolbar: toolbarOptions,
                }}
                style={{
                    boxSizing: "border-box",
                    width: "100%",
                    height: "600px",
                }}
                value={inputValue.content}
                onChange={handleQuillOnChange}
            />
        </div>
    ); // ReactQuill 컴포넌트
}

export default WritePage;