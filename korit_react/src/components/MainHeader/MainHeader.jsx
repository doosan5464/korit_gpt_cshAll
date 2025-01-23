/**@jsxImportSource @emotion/react */
import axios from 'axios';
import * as s from './style';
import React, { useEffect, useState } from 'react';
import { LuLayoutList, LuLogIn, LuLogOut, LuUser, LuNotebookPen, LuUserRoundPlus } from 'react-icons/lu'; // react icons 에서 들고옴
import { Link } from 'react-router-dom'; // Link는 곧 a 태그로 바꿔 사용하기 때문에 & a 스타일링이 적용된다
import { useRecoilState } from 'recoil';
import { authUserIdAtomState } from '../../atoms/authAtom';


// a 태그는 전체적으로 재렌더링이 일어나버림
// Link는 부분적으로만 재렌더링이 일어남. 구분하여 사용
// Link to={"/~~"} : 원하는 경로로
function MainHeader(props) {
    const [ userId, setUserId ] = useRecoilState(authUserIdAtomState); // authAtom 파일을 만들어놓고 전역적으로 접근
    const [ loadStatus, setLoadStatus ] = useState("idle"); // loading, success

    const getUserApi = async (userId) => {
        try {
            const response = await axios.get("http://localhost:8080/servlet_study_war/api/user", {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("AccessToken"), // 서버에 요청할때 AccessToken있는사람만 
                },
                params: {
                    "userId": userId,
                }
            });
            console.log(response);
        } catch (error) {
            
        }
    }

    useEffect(() => {
        if(!!userId) {
            getUserApi(userId);
        }
    }, [userId]); // 로그인이 됐거나 로그아웃이 됐거나

    return (
        <div css={s.layout}>
            <div css={s.leftContainer}>
                <Link to={"/"} ><h1>미니 게시판 프로젝트</h1></Link>
                <ul>
                    <Link to={"/list"}>
                        <li>
                            <LuLayoutList />게시글 목록
                        </li>
                    </Link>
                    <Link to={"/write"}>
                        <li>
                            <LuNotebookPen />게시글 작성
                        </li>
                    </Link>
                </ul>
            </div>
            <div css={s.rightContainer}>
                {
                    !!userId ?
                    <ul>
                        <Link to={"/mypage"} >
                            <li><LuUser />사용자이름</li>
                        </Link>
                        <Link to={"/logout"} >
                            <li><LuLogOut />로그아웃</li>
                        </Link>
                    </ul>
                    :
                    <ul>
                        <Link to={"/signin"} >
                            <li><LuLogIn />로그인</li>
                        </Link>
                        <Link to={"/signup"} >
                            <li><LuUserRoundPlus />회원가입</li>
                        </Link>
                    </ul>
                }
            </div>
        </div>
    )
}

export default MainHeader;