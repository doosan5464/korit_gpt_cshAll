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
    const joinMutation = useJoinMutation(); // authMutation중 joinMutation. 회원가입 - post요청

    const [ inputValue, setInputValue ] = useState({ // 회원가입때 넣을 정보들
        username: "",
        email: "",
        password: "",
        passwordCheck: "",
    }); 
    const handleInputOnChange = (e) => { // 회원가입 창 최신화
        setInputValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    }
    const handlePasswordOnFocus = () => { // 비밀번호 입력창을 클릭하면(onFocus), 기존에 입력한 비밀번호와 비밀번호 확인을 자동으로 지워줌
        setInputValue(prev => ({
            ...prev,
            password: "",
            passwordCheck: "",
        }));
    }

    // 에러가 있는지 확인하는 Hook함수. false면 정상인거고 true면 밑에서 에러처리하면서 바꾼거임
    const [ inputValidError, setInputValidError ] = useState({
        username: false,
        email: false,
        password: false,
        passwordCheck: false,
    });
    const isErrors = () => { // 최종적으로 회원가입 버튼을 누를시 검사할 에러 함수
        const isEmpty = Object.values(inputValue).map(value => !!value).includes(false); // !!로 논리형으로 바꾸면서 빈값이면 false가 됨
        const isValid = Object.values(inputValidError).includes(true); 
        return isEmpty || isValid; // 둘 중 하나라도 true면 true 반환
    }




    const handleJoinOnClick = () => {
        if(isErrors()) {
            alert("가입 정보를 다시 확인해주세요.");
            return;
        }
        
        joinMutation.mutateAsync({ // 회원가입 post 요청에 inputValue 속성들 투입
            username: inputValue.username, 
            email: inputValue.email, 
            password: inputValue.password,
        }).then(response => { // .then() : Promise(프로미스)가 성공적으로 완료된 후 실행할 콜백 함수를 지정하는 메서드
            alert("가입해 주셔서 감사합니다.");
            navigate(`/auth/login?username=${response.data.username}`);
            // 회원가입이 성공한 후, 로그인 페이지(/auth/login)로 이동하면서 username을 URL에 쿼리 파라미터로 포함
        }).catch(error => {
            if(error.status === 400){
                setInputValidError(prev => ({ // 에러가 있다면 true 설정 (통과 못하게 하려고)
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