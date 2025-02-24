/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useState } from 'react';
import { SiGoogle, SiKakao, SiNaver } from "react-icons/si";
import { Link, useNavigate } from 'react-router-dom';
import ValidInput from '../../components/auth/ValidInput/ValidInput';
import { useInputValid } from '../../hooks/validInputHook';
import { useJoinMutation } from '../../mutations/authMutation';

function JoinPage(props) {
    const navigate = useNavigate();
    const joinMutaion = useJoinMutation();

    const [ inputValue, setInputValue ] = useState({
        username: "",
        email: "",
        password: "",
        passwordCheck: "",
    }); 
    const handleInputOnChange = (e) => {
        setInputValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    }

    const [ inputValidError, setInputValidError ] = useState({
        username: false,
        email: false,
        password: false,
        passwordCheck: false,
    });

    

    const isErrors = () => {
        const isEmpty = Object.values(inputValue).map(value => !!value).includes(false);
        const isValid = Object.values(inputValidError).includes(true);
        return isEmpty || isValid;
    }

    const handlePasswordOnFocus = () => {
        setInputValue(prev => ({
            ...prev,
            password: "",
            passwordCheck: "",
        }));
    }

    const handleJoinOnClick = () => {
        if(isErrors()) {
            alert("가입 정보를 다시 확인해주세요.");
            return;
        }
        
        joinMutaion.mutateAsync({
            username: inputValue.username, 
            email: inputValue.email, 
            password: inputValue.password,
        }).then(response => {
            alert("가입해 주셔서 감사합니다.");
            navigate(`/auth/login?username=${response.data.username}`);
        }).catch(error => {
            if(error.status === 400){
                setInputValidError(prev => ({
                    ...prev,
                    username: true,
                }));
            }
        })
    }

    return (
        <div css={s.layout}>
            <div>
                <header>
                    <h1 css={s.title1}>Think it. Make it.</h1>
                    <h1 css={s.title2}>Log in to your Board account</h1>
                </header>
                <main>
                    <div css={s.oauth2Group}>
                        <div css={s.groupBox}>
                            <button css={s.oauth2Button}>
                                <div css={s.oauth2Icon}><SiGoogle /></div>
                                <span css={s.oauth2Text}>Continue with Google</span>
                            </button>
                        </div>
                        <div css={s.groupBox}>
                            <button css={s.oauth2Button}>
                                <div css={s.oauth2Icon}><SiNaver /></div>
                                <span css={s.oauth2Text}>Continue with Naver</span>
                            </button>
                        </div>
                        <div css={s.groupBox}>
                            <button css={s.oauth2Button}>
                                <div css={s.oauth2Icon}><SiKakao /></div>
                                <span css={s.oauth2Text}>Continue with Kakao</span>
                            </button>
                        </div>
                    </div>
                    <div>
                        <ValidInput type={"text"} placeholder={"Enter your username..."} 
                            name={"username"}
                            value={inputValue.username}
                            onChange={handleInputOnChange}
                            regexp={/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/}
                            errorMessage={"사용할 수 없는 사용자이름입니다."}
                            inputValidError={inputValidError}
                            setInputValidError={setInputValidError} />
                        <ValidInput type={"text"} placeholder={"email address..."} 
                            name={"email"}
                            value={inputValue.email}
                            onChange={handleInputOnChange}
                            regexp={/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/}
                            errorMessage={"올바른 이메일을 입력하세요."}
                            inputValidError={inputValidError}
                            setInputValidError={setInputValidError} />
                        <ValidInput type={"password"} placeholder={"password..."} 
                            name={"password"}
                            value={inputValue.password}
                            onChange={handleInputOnChange}
                            onFocus={handlePasswordOnFocus}
                            regexp={/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,20}$/}
                            errorMessage={"비밀번호는 8자에서 16자 이하로 영문, 숫자 조합이어야합니다."}
                            inputValidError={inputValidError}
                            setInputValidError={setInputValidError} />
                        <ValidInput type={"password"} placeholder={"password check..."} 
                            name={"passwordCheck"}
                            value={inputValue.passwordCheck}
                            onChange={handleInputOnChange}
                            regexp={new RegExp(`^${inputValue.password}$`)}
                            errorMessage={"비밀번호가 일치하지 않습니다."}
                            inputValidError={inputValidError}
                            setInputValidError={setInputValidError} />
                        
                        
                        <p css={s.accountMessage}>
                            계정이 이미 있으신가요? <Link to={"/auth/login"}>로그인</Link>
                        </p>
                        <div css={s.groupBox}>
                            <button css={s.accountButton} onClick={handleJoinOnClick}>Join</button>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
}

export default JoinPage;