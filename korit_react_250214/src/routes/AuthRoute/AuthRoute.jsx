import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import SigninPage from '../../pages/SigninPage/SigninPage';
import SignupPage from '../../pages/SignupPage/SignupPage';
import { useQueryClient } from '@tanstack/react-query';

function AuthRoute(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    console.log(queryClient.getQueryState(["userQuery"]));
    console.log(queryClient.getQueryData(["userQuery"]));

    const isLogin = !!queryClient.getQueryData(["userQuery"]); // AccessToken이 있는지 확인(논리형)


    useEffect(() => {
        if(isLogin) {
            navigate("/"); // 로그인이 된 상태라면 인덱스 페이지로
        }
    }, []); // 의존성 배열자리에 빈 배열 -> 마운트 될 때 1번만 실행

    return (
        <>
            {
                !isLogin && // 로그인, 회원가입 -> 로그인이 안된 상태에서 떠야하는 페이지목록
                <Routes>
                    <Route path="/signin" element={<SigninPage />} />
                    <Route path="/signup" element={<SignupPage />} />
                </Routes>
            }
        </>
    );
}

export default AuthRoute;