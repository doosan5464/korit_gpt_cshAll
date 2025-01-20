import React, { useEffect, useState } from 'react';
import Route from './Route';

function RouterDom({ children }) {
    const [ pathname, setPathname ] = useState(window.location.pathname);  
    // URL 경로를 추적해서 자식 컴포넌트를 렌더링하려 함
    // window.location.pathname : 현재 페이지의 URL 경로 (현재 초기값으로 주고 있음)

    console.log(pathname)

    useEffect(() => {
        setPathname(window.location.pathname);
    }, [window.location.pathname])
    // ???????????????????????????????????????????????????
    
    return (
        <div>
            {children}
        </div>
    ); // {children} : RouterDom 컴포넌트의 자식 컴포넌트들이 전달되는 부분
}

export default RouterDom;