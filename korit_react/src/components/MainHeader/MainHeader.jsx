/**@jsxImportSource @emotion/react */
import axios from 'axios';
import * as s from './style';
import React, { useEffect, useState } from 'react';
import { LuLayoutList, LuLogIn, LuLogOut, LuUser, LuNotebookPen, LuUserRoundPlus } from 'react-icons/lu'; // react icons 에서 들고옴
import { Link } from 'react-router-dom'; // Link는 곧 a 태그로 바꿔 사용하기 때문에 & a 스타일링이 적용된다
import { useRecoilState } from 'recoil';
import { authUserIdAtomState } from '../../atoms/authAtom';
import { useQuery, useQueryClient } from 'react-query';


// a 태그는 전체적으로 재렌더링이 일어나버림
// Link는 부분적으로만 재렌더링이 일어남. 구분하여 사용
// Link to={"/~~"} : 원하는 경로로
function MainHeader(props) {
    const queryClient = useQueryClient(); //  authenticatedUserQuery : App.js의 useQuery의 키 
    const userId = queryClient.getQueryData(["authenticatedUserQuery"])?.data.body; // ?. : 옵셔널 체이닝(optional chaining) 연산자 -> 객체가 null이나 undefined인 경우 에러를 던지지 않고, 대신 undefined를 반환
    console.log(userId);
    console.log(queryClient.isFetching({
        queryKey: ["authenticatedUserQuery"]
    }))

    const getUserApi = async () => {
        return await axios.get("http://localhost:8080/servlet_study_war/api/user", {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("AccessToken"), // 서버에 요청할때 AccessToken있는사람만 
                },
                params: {
                    "userId": userId,
                }
            });
    }

    const getUserQuery = useQuery(
        ["getUserQuery", userId], // userId는 의존성 : 바뀌면 다시 요청
        getUserApi,
        {
            refetchOnWindowFocus: false,
            enabled: !!userId, // 조건부 실행: userId가 있을 때만 Query 실행
        }
    );

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
                    // getUserQuery -> getUserApi -> isLoading? 
                    <ul>
                        <Link to={"/mypage"} >
                            <li><LuUser />{getUserQuery.isLoading ? "" : getUserQuery.data.data.username}</li>
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