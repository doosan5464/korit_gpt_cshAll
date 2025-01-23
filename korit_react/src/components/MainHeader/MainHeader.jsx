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
    const queryClient = useQueryClient(); // index.js 의 리액트쿼리와 공유하는 전역 상태 저장소
    
    // 현재 로그인한 사용자 ID 가져오기, authenticatedUserQuery : App.js의 useQuery의 키 
    const userId = queryClient.getQueryData(["authenticatedUserQuery"])?.data.body; 
    // ?. : 옵셔널 체이닝(optional chaining) 연산자 -> 객체가 null이나 undefined인 경우 에러를 던지지 않고, 대신 undefined를 반환 (에러 방지)
    console.log(userId);
    console.log(queryClient.isFetching({
        queryKey: ["authenticatedUserQuery"]
    }))

    // 사용자 정보 가져오기 쿼리
    const getUserQuery = useQuery(
        ["getUserQuery", userId], // userId는 의존성 : userId가 변경될 때마다 쿼리가 다시 실행
        getUserApi,
        {
            refetchOnWindowFocus: false,
            enabled: !!userId, // 조건부 실행: userId가 있을 때만 Query 실행
        }
    );

    // API로 사용자 정보 가져오기 (Query 함수)
    const getUserApi = async () => {
        return await axios.get("http://localhost:8080/servlet_study_war/api/user", {
                headers: { // 요청할 때 헤더에 Authorization 토큰을 포함하여 인증 정보를 전달
                    "Authorization": "Bearer " + localStorage.getItem("AccessToken"), // 서버에 요청할때 AccessToken있는사람만 
                },
                params: { // params 객체를 통해 userId를 쿼리 파라미터로 전달
                    "userId": userId,
                }
            });
    }
    /*
    비동기 요청 지연
    
    userId는 비동기 요청을 통해 서버에서 받아오는 데이터
    페이지 새로고침 시, userId를 가져오기 전에 getUserQuery가 실행되면, 아직 userId가 없으므로 로그인 상태가 아닌 상태처럼 보임
    이후 userId가 비동기적으로 받아지면 getUserQuery가 다시 실행되고 올바른 상태로 업데이트

    옵셔널 체이닝(?.) : userId를 가져올 때 오류를 방지하고 값을 안전하게 처리

    enabled: !!userId 옵션을 사용하여, userId가 있는 경우에만 사용자 정보를 가져오는 쿼리를 실행

    getUserQuery.isLoading를 활용해, 사용자 데이터가 로딩 중일 때 오른쪽 메뉴의 버튼을 빈 상태로 표시
    */

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