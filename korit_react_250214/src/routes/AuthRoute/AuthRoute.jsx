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
    const isLogin = !!queryClient.getQueryData(["userQuery"]);

    useEffect(() => {
        if(isLogin) {
            navigate("/");
        }
    }, []);

    return (
        <>
            {
                !isLogin &&
                <Routes>
                    <Route path="/signin" element={<SigninPage />} />
                    <Route path="/signup" element={<SignupPage />} />
                </Routes>
            }
        </>
    );
}

export default AuthRoute;