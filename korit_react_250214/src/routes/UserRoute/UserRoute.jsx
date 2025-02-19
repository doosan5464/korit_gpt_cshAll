import React, { useEffect } from 'react';
import { useQueryClient } from '@tanstack/react-query';
import { Route, Routes, useNavigate } from 'react-router-dom';
import ProfilePage from '../../pages/ProfilePage/ProfilePage';

function UserRoute(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    const isLogin = !!queryClient.getQueryData(["userQuery"]);

    useEffect(() => {
        if(!isLogin) {
            alert("잘못된 접근입니다.");
            navigate("/auth/signin"); // 로그인이 안되어있다면 로그인 페이지로
        }
    }, []); // 마운트 될 때 1번 실행

    return (
        <>
            {
                isLogin && // 로그인이 되어있다면 프로필 페이지가 활성화
                <Routes>
                    <Route path='/profile' element={<ProfilePage />} />
                </Routes>
            }  
        </>
    );
}

export default UserRoute;