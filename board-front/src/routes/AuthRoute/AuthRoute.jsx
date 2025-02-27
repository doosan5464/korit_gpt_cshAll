import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import LoginPage from '../../pages/LoginPage/LoginPage';
import JoinPage from '../../pages/JoinPage/JoinPage';
import NotFoundPage from '../../pages/NotFoundPage/NotFoundPage';
import { useQueryClient } from '@tanstack/react-query';

function AuthRoute(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();
    const queryState = queryClient.getQueryState(["userMeQuery"]);

    useEffect(() => {
        if(queryState.status === "success") {
            navigate("/");
        }
    }, [queryState]);

    return queryState.status === "error" &&
        <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/join" element={<JoinPage />} />
            <Route path="/*" element={<NotFoundPage />} />
        </Routes>
}

export default AuthRoute;