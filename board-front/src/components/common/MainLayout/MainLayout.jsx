/**@jsxImportSource @emotion/react */
import * as s from './style';
import React from 'react';

function MainLayout({ children }) { // 하위 컴포넌트 렌더링
    return (
        <div css={s.layout}>
            {children}
        </div>
    );
}

export default MainLayout;