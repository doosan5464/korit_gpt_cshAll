import React, { useEffect, useRef, useState } from 'react';
/**@jsxImportSource @emotion/react */
import * as s from './style';
import axios from 'axios';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';

// 로그인은 doGet 일것같지만 doPost임. create속성이 있기 때문임
function SigninPage(props) {
    const navigate = useNavigate();

    // Hook함수. useSearchParams()
    const [ searchParams ] = useSearchParams(); 

    // 요소에 직접 접근하기 위해 useRef사용, setter는 필요없으니까 지움(비구조 할당?)
    // 이렇게하면 변수를 따로 안만들고 배열의 인덱스로 관리 가능
    const [ inputRefs ] = useState([ useRef(), useRef(), ]);
    const [ buttonRefs ] = useState([ useRef() ]); 

    const [ inputValue, setInputValue ] = useState({
        username: "",
        password: "",
    });

    // 주소창에 있는 username을 그대로 로그인 아이디 입력창에 넣어둠
    useEffect(() => {
        setInputValue({
            ...inputValue,
            username: searchParams.get("username") || "", // false일 때 무항의 값을 줌
        });
    }, [searchParams.get("username")]);

    const handleInputOnChange = (e) => {
        setInputValue({
            ...inputValue,
            [e.target.name]: e.target.value,
        });
    }

    const handleInputOnKeyDown = (e) => {
        // enter
        if(e.keyCode === 13) {
            let foundIndex = -1; // 인덱스랑 안겹칠려고
            for(let i = 0; inputRefs.length; i++) {
                if(inputRefs[i].current === e.target) {
                    foundIndex = i;
                    break;
                }
            }
            // 마지막 인덱스
            if(foundIndex ===  inputRefs.length - 1) {
                buttonRefs[0].current.click();
                return;
            }
            inputRefs[foundIndex + 1].current.focus();
        }
    }
    const handleSigninSubmitOnClick = async () => {
        try {
            const response = await axios.post("http://localhost:8080/servlet_study_war/api/signin", inputValue);
            localStorage.setItem("AccessToken", response.data.body); 
            // localStorage에 키(AccessToken) 값(response.data.body) 형태로 응답 데이터를 넣는다
            navigate("/"); // 성공하면 home으로
        } catch (error) {
            console.error(error);
        }
    }
 
    return (
        <div css={s.layout}>
            <div css={s.main}>
                <input type="text" 
                    placeholder='사용자 이름' 
                    name='username' 
                    value={inputValue.username} 
                    onChange={handleInputOnChange} 
                    onKeyDown={handleInputOnKeyDown} 
                    ref={inputRefs[0]} />
                <input type="password" 
                    placeholder='비밀번호' 
                    name='password' 
                    value={inputValue.password} 
                    onChange={handleInputOnChange} 
                    onKeyDown={handleInputOnKeyDown} 
                    ref={inputRefs[1]} />
                <button onClick={handleSigninSubmitOnClick} ref={ buttonRefs[0] } >로그인</button>
            </div>
            <div css={s.footer}>
                <span>계정이 없으신가요? </span>
                <Link to={"/signup"}>회원가입</Link>
            </div>
        </div>
    );
}

export default SigninPage;