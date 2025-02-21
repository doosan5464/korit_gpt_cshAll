/**@jsxImportSource @emotion/react */
import * as s from './style';
import React from 'react';
import { SiGoogle, SiKakao, SiNaver } from "react-icons/si";
import { Link } from 'react-router-dom';

function JoinPage(props) {
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
                            <input css={s.textInput} type="text" placeholder='Enter your email address...' />
                        </div>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="password" placeholder='password...' />
                        </div>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="password" placeholder='password check...' />
                        </div>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="text" placeholder='nickname' />
                        </div>
                        <p css={s.accountMessage}>
                            계정이 이미 있으신가요? <Link to={"/auth/login"}>로그인</Link>
                        </p>
                        <div css={s.groupBox}>
                            <button css={s.accountButton}>Login</button>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
}

export default JoinPage;