/**@jsxImportSource @emotion/react */
import * as s from './style';
import React from 'react';
import { LuLayoutList, LuLogIn, LuNotebookPen, LuUserRoundPlus } from 'react-icons/lu'; // react icons 에서 들고옴
import { Link } from 'react-router-dom'; // Link는 곧 a 태그로 바꿔 사용하기 때문에 & a 스타일링이 적용된다


// a 태그는 전체적으로 재렌더링이 일어나버림
// Link는 부분적으로만 재렌더링이 일어남. 구분하여 사용
// Link to={"/~~"} : 원하는 경로로
function MainHeader(props) {
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
                <ul>
                    <Link to={"/signin"} >
                        <li><LuLogIn />로그인</li>
                    </Link>
                    <Link to={"/signup"} >
                        <li><LuUserRoundPlus />회원가입</li>
                    </Link>
                </ul>
            </div>
        </div>
    )
}

export default MainHeader;