import React, { useRef, useState } from 'react';
/**@jsxImportSource @emotion/react */
import * as s from './style';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

function SignupPage(props) {
    // navigate : Route나 RouterDom 에서만 사용 가능
    const navigate = useNavigate();
    
    // 요소에 직접 접근하기 위해 useRef사용, setter는 필요없으니까 지움(비구조 할당?)
    // 이렇게하면 변수를 따로 안만들고 배열의 인덱스로 관리 가능
    const [ inputRefs ] = useState([ useRef(), useRef(), useRef(), useRef() ]);
    const [ buttonRefs ] = useState([ useRef() ]); 

    const [ inputValue, setInputValue ] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });

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
    const handleSignupSubmitOnClick = async () => {
        try {
            const response = await axios.post("http://localhost:8080/servlet_study_war/api/signup", inputValue);
            console.log(response);
            alert("회원가입 완료!");
            navigate(`/signin?username=${response.data.data.username}`); // 로그인창으로 가고, 방금 가입한 내 아이디가 주소창에 받아옴
            
        } catch (error) {
            
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
                <input type="text" 
                    placeholder='성명' 
                    name='name' 
                    value={inputValue.name} 
                    onChange={handleInputOnChange} 
                    onKeyDown={handleInputOnKeyDown} 
                    ref={inputRefs[2]} />
                <input type="text" 
                    placeholder='이메일 주소' 
                    name='email' 
                    value={inputValue.email} 
                    onChange={handleInputOnChange} 
                    onKeyDown={handleInputOnKeyDown} 
                    ref={inputRefs[3]} />
                <button onClick={handleSignupSubmitOnClick} ref={ buttonRefs[0] } >가입</button>
            </div>
            <div css={s.footer}>
                <span>계정이 있으신가요? </span>
                <Link to={"/signin"}>로그인</Link>
            </div>
        </div>
    );
}

export default SignupPage;