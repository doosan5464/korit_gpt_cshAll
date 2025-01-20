import React, { useState } from 'react';
import Signin from './components/sign/Signin/Signin';
import Signup from './components/sign/Signup/Signup';

function App6(props) {
    const [path, setPath] = useState("signin");
    const [userList, setUserList] = useState([]);

    // e는 그냥 학습용으로 넣은 것. 아무데도 안 씀
    const handlePageChangeButtonOnClick = (e, path) => {
        setPath(path);
    };

    // onClick에서 화살표함수를 쓰면 함수에 매개변수를 넣을 수 있다
    // 원래는 함수호출된 결과를 담았는데 이러면 함수를 호출함(함수()로 함수handlePageChangeButtonOnClick를)
    return (
        <div>
            <button onClick={(e) => handlePageChangeButtonOnClick(e, ("signin"))}>로그인</button>
            <button onClick={(e) => handlePageChangeButtonOnClick(e, ("signup"))}>회원가입</button>
            {   // jsx의 조건부 렌더링 : && 연산자는 왼쪽의 조건이 true일 때만 오른쪽의 컴포넌트가 렌더링되는 방식
                // 컴포넌트 함수에서 JSX를 반환하는 것만으로 렌더링이 발생
                path === "signin" && <Signin userList={userList}/> // path 값이 "signin"일 때만 Signin 컴포넌트를 렌더링
            }
            {
                path === "signup" && <Signup userList={userList} setUserList={setUserList}/> 
            }
        </div>
    );
}

export default App6;