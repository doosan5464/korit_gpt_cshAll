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
        // { children } 은 자식 요소를 부모 컴포넌트에 동적으로 전달할 때 사용
        // 이제 App.js에서 이 MainLayout을 쓰면
        
        /*

            <MainLayout>
                자식 요소들
            </MainLayout>
            
        */
       // 저기 있는 자식 요소들이 { children } 로 와서 렌더링한다
    );
}

export default MainLayout;