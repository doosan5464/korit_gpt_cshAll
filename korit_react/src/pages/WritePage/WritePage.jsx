/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useEffect } from 'react';
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

    return (
        <div>
            <div css={s.headerLayout}>
                <button>작성하기</button>
            </div>
            <ReactQuill 
                modules={{
                    toolbar: toolbarOptions,
                }}
            />
        </div>
    );
}

export default WritePage;