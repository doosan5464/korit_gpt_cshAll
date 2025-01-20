import React, { useState } from 'react';

function App5Self(props) {
    /*
    회원 정보를 입력받아 userList에 user객체들을 가입하기 버튼을 누를 때마다 저장한다
    로그인 정보를 입력받아 userList에 해당 username이 있는지 확인하고
    없으면 '사용자 정보를 다시 입력하세요.' 메세지 출력
    있으면 비밀번호가 일치하는지 검사
    비밀번호가 일치하지 않으며 '사용자 정보를 다시 입력하세요.' 메세지 출력
    일치하면 이름(이메일)님 환영합니다. 출력(alert)
    */


    const [userInfo, setUserInfo] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });


    const [storage, setStorage] = useState([])


    const [inputUserInfo, setInputUserInfo] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });


    const handleRegister = (e) => {
        const {name, value} = e.target;
        console.log(name, value);

        setInputUserInfo({ // inputUserInfo를 객체로 선언했으니 넣을때도 객체형태 {}로
            ...inputUserInfo,
            [name]: value,
        });
    }


    const handleRegisterButton = () => {
        setUserInfo({
            username: inputUserInfo.username,
            password: inputUserInfo.password,
            name: inputUserInfo.name,
            email: inputUserInfo.email,
        });
        
        setInputUserInfo({
            ...inputUserInfo,
            username: "",
            password: "",
            name: "",
            email: "",
        });
        console.log(inputUserInfo)

        setStorage([...storage, 
                    inputUserInfo]); // storage를 []로 선언했으니 넣을대도 []로
        /*
        ...storage 스프레드로 이전에 있던 키:값 형태의 배열들을 한 배열씩 새로 추가하고 
        inputUserInfo와 중복된 키가 없다면 그 밑에 새로운 키:값 추가하듯이 배열이 추가된다
        */
        console.log(storage); // 비동기
    }
    console.log(storage); // 여기서 출력됨. 근데 그 다음부터 배열에 안들어감






    const [loginInfo, setLoginInfo] = useState({
        username: "",
        password: "",
    });


    const handleLogin = (e) => {
        const {name, value} = e.target;
        console.log(name, value);

        setLoginInfo({
            ...loginInfo,
            [name]: value,
        });
    }

    
    const handleLoginCheck = () => {
        let num = 0;
        let check = false;
        let finalNum = 0;
        storage.forEach(element => {
            if(element.username === loginInfo.username && element.password === loginInfo.password) {
                check = true;
                finalNum = num;
            } else {
                num++;
            }
        });
        if(check) {
            return alert(`${storage[finalNum].username}(${storage[finalNum].email})님 환영합니다`);
        } else {
            return alert("사용자 정보를 다시 입력하세요.");        
        }
    }


    return (
        <div>
            <h1>회원가입</h1>
            <input type="text" name="username" onChange={handleRegister} placeholder='username' value={inputUserInfo.username} />
            <input type="password" name="password" onChange={handleRegister} placeholder='password' value={inputUserInfo.password} />
            <input type="text" name="name" onChange={handleRegister} placeholder='name' value={inputUserInfo.name} />
            <input type="text" name="email" onChange={handleRegister} placeholder='email' value={inputUserInfo.email} />
            <div>
                <button onClick={handleRegisterButton}>가입하기</button>
            </div>

            <h1>로그인</h1>
            <input type="text" name='username' onChange={handleLogin} placeholder='username' value={loginInfo.username} />
            <input type="password" name='password' onChange={handleLogin} placeholder='password' value={loginInfo.password} />
            <div>
                <button onClick={handleLoginCheck}>로그인</button>
            </div>
        </div>
    );
}

export default App5Self;