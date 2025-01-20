/*
도서정보 등록 및 조회

도서명, isbn, 저자명, 출판사명, 카테고리 
입력받아서 bookList에 저장

table
tbody
tr
td

컴포넌트는 2개로

더 하고싶으면 아무거나 추가

*/
import React, { useState } from 'react';
import Signin from './App7Self/Signin';
import Signup from './App7Self/Signup';

function App7(props) {
        const [path, setPath] = useState("signin");
        const [userList, setUserList] = useState([]);

        const handlePageButton = (path) => {
            setPath(path);
        }
        
    return (
        <div>
            <button onClick={() => handlePageButton("signin")}>조회</button>
            <button onClick={() => handlePageButton("signup")}>등록</button>
            {   
                path === "signin" && <Signin userList={userList}/> 
            }
            {
                path === "signup" && <Signup userList={userList} setUserList={setUserList}/> 
            }

        </div>
        
        
        
        
    );
}

export default App7;