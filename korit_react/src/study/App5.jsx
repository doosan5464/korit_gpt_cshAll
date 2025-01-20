import React, { useState } from 'react';

/**
 * 회원 정보를 입력받아 userList에 user객체들을 가입하기 버튼을 누를 때마다 저장한다.
 * 로그인 정보를 입력받아 userList에 해당 username이 있는지 확인하고
 * 없으면 '사용자 정보를 다시 확인하세요.' 메세지 출력(alert)
 * 있으면 비밀번호가 일치하는지 검사
 * 비밀번호가 일치하지 않으면 '사용자 정보를 다시 확인하세요.' 메세지 출력(alert)
 * 일치하면 이름(이메일)님 환영합니다. 출력(alert)
 */

function App5(props) {
    const [ userList, setUserList ] = useState([]);

    const [ signupInputValue, setSignupInputValue ] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });

    const [ signinInputValue, setSigninInputValue ] = useState({
        username: "",
        password: "",
    });


    const handleSignupInputOnChange = (e) => {
        setSignupInputValue({
            ...signupInputValue,
            [e.target.name]: e.target.value,
        });
    };

    const handleSignupButtonOnClick = () => {
        setUserList([
            ...userList,
            signupInputValue,
        ]);

        alert("가입완료.");

        setSignupInputValue({
            username: "",
            password: "",
            name: "",
            email: "",
        });
    };

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
            <h1>회원가입</h1>
            <input type="text" name='username' placeholder='username'       onChange={handleSignupInputOnChange} value={signupInputValue.username} />
            <input type="password" name='password' placeholder='password'   onChange={handleSignupInputOnChange} value={signupInputValue.password} />
            <input type="text" name='name' placeholder='name'               onChange={handleSignupInputOnChange} value={signupInputValue.name} />
            <input type="text" name='email' placeholder='email'             onChange={handleSignupInputOnChange} value={signupInputValue.email} />
            <div>
                <button onClick={handleSignupButtonOnClick}>가입하기</button>
            </div>
            <h1>로그인</h1>
            <input type="text" name='username' placeholder='username'       onChange={handleSigninInputOnChange} value={signinInputValue.username} />
            <input type="password" name='password' placeholder='password'   onChange={handleSigninInputOnChange} value={signinInputValue.password} />
            <div>
                <button onClick={handleSigninButtonOnClick}>로그인</button>
            </div>
        </div>
    );
}

export default App5;