import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import AccountPage from '../../pages/AccountPage/AccountPage';
import MainSidebar from '../../components/common/MainSidebar/MainSidebar';
import MainContainer from '../../components/common/MainContainer/MainContainer';
import { useUserMeQuery } from '../../queries/userQuery';
import NotFoundPage from '../../pages/NotFoundPage/NotFoundPage';
import { useQueryClient } from '@tanstack/react-query';

function MainRoute(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();
    const queryState = queryClient.getQueryState(["userMeQuery"]);

    useEffect(() => {
        if(queryState.status === "error") {
            navigate("/auth/login");
        }
    }, [queryState]);

    return queryState.status === "success" &&
        <>
            <MainSidebar />
            <MainContainer>
                <Routes>
                    <Route path="/account/setting" element={<AccountPage />} />
                    
                    <Route path="/*" element={<NotFoundPage />} />
                </Routes>
            </MainContainer>
        </>
}

export default MainRoute;