/**@jsxImportSource @emotion/react */
import MainHeader from "../MainHeader/MainHeader";
import * as s from "./style";
import React from 'react';

function MainLayout({ children }) {
    return (
        <div css={s.layout}>
            <MainHeader />
            { children } 
        </div> 
        // css={s.layout} : 기본 css을 테두리처럼 큰 틀로 정해둠
        // <MainHeader /> : 어느 페이지를 가든 항상 있을 구성요소들
        // { children } 은 Main
    );
}

export default MainLayout;