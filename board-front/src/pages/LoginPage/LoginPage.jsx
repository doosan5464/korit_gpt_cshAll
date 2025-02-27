/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useState } from 'react';
import { SiGoogle, SiKakao, SiNaver } from "react-icons/si";
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import { useLoginMutation } from '../../mutations/authMutation';
import Swal from 'sweetalert2';
import { setTokenLocalStorage } from '../../configs/axiosConfig';
import { useQueryClient } from '@tanstack/react-query';


function LoginPage(props) {
    const navigate = useNavigate();

    // 로그인 Mutate 가져옴
    const loginMutation = useLoginMutation();
    // @@@@@@@@@@@@@@@@@@@@@@@@@@
    const queryClient = useQueryClient();


    // 검색정보를 담을 상태 변수
    /*
    useSearchParams()
    : React Router에서 URL의 **쿼리 스트링(Query String)**을 다룰 때 사용하는 Hook 함수 (내장되어 있음)
      ?key=value 형태의 URL 파라미터를 읽고, 변경
    */
    const [ searchParams, setSearchParams ] = useSearchParams();


    // 로그인정보를 담을 상태변수
    /*
    useState
    : React의 상태(state)를 관리하는 기본 Hook 함수
    */
    const [ inputValue, setInputValue ] = useState({
        username: searchParams.get("username") || "", // URL의 쿼리 스트링에서 ?username=temp 즉, username을 가져옴
        password: ""
    }); 


    const handleInputOnChange = (e) => {
        setInputValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    }


    const handleLoginOnClick = async () => {
        try {
            const response = await loginMutation.mutateAsync(inputValue); // await이 끝날때까지 밑으로 코드 실행 x
            // .mutateAsync() :  비동기적으로 mutation을 수행하고, 프로미스를 반환
            const tokenName = response.data.name;
            const accessToken = response.data.token;
            
            setTokenLocalStorage(tokenName, accessToken);

            await Swal.fire({ // Swal -> 외부 라이브러리
                position: "center",
                icon: "success",
                title: "로그인 성공!",
                showConfirmButton: false,
                timer: 1500,
            });
            await queryClient.invalidateQueries({queryKey: ["userMeQuery"]}); // .invalidateQueries({querykey:["키이름"]}) : querykey와 동일한 키의 쿼리 캐시를 무효 -> 다시 요청하게 함
            navigate("/");
        } catch(error) {    
            await Swal.fire({ // await -> 비동기로 실행하니까 붙임. 지금은 딱히 특징적으로 동작안함
                title: '로그인 실패!',
                text: '사용자 정보를 다시 확인해주세요',
                confirmButtonText: '확인',
                confirmButtonColor: "#e22323"
            });
        }
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
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="text" placeholder='Enter your username...'
                                name="username"
                                value={inputValue.username}
                                onChange={handleInputOnChange}
                            />
                        </div>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="password" placeholder='password...'
                                name="password"
                                value={inputValue.password}
                                onChange={handleInputOnChange}
                            />
                        </div>
                        <p css={s.accountMessage}>
                            계정이 없으시다면 지금 가입하세요. <Link to={"/auth/join"}>회원가입</Link>
                        </p>
                        <div css={s.groupBox}>
                            <button css={s.accountButton} onClick={handleLoginOnClick}>Login</button>
                        </div>
                    </div>
                </main>
                <footer>
                    <p css={s.footerAgreement}>
                        이메일을 사용하여 계정을 구분하고 다른 사용자들에게 게시글을 공유합니다.
                        계속 진행하려면 약관 및 개인정보 보호정책을 이해하고 동의한다는 것을 인정해야합니다.
                    </p>
                </footer>
            </div>
        </div>
    );
}

export default LoginPage;