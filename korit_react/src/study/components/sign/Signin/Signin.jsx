import React, { useState } from 'react';  // 이 줄 추가


function Signin({userList}) {
    const [ signinInputValue, setSigninInputValue ] = useState({
        username: "",
        password: "",
    });

    const handleSigninInputOnChange = (e) => {
        setSigninInputValue({
            ...signinInputValue,
            [e.target.name]: e.target.value,
        });
    };

    const handleSigninButtonOnClick = () => {
        const foundUser = userList.find(user => user.username === signinInputValue.username);
        if(!foundUser) {
            alert("사용자 정보를 다시 확인하세요.");
            return;
        }
        if(foundUser.password !== signinInputValue.password) {
            alert("사용자 정보를 다시 확인하세요.");
            return;
        }
        alert(`${foundUser.name}(${foundUser.email})님 환영합니다.`);
    };


    return (
        <div>
            <h1>로그인</h1>
            <input type="text" name='username' placeholder='username'       onChange={handleSigninInputOnChange} value={signinInputValue.username} />
            <input type="password" name='password' placeholder='password'   onChange={handleSigninInputOnChange} value={signinInputValue.password} />
            <div>
                <button onClick={handleSigninButtonOnClick}>로그인</button>
            </div>
        </div>
    );
}

export default Signin;