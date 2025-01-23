import { Route, Routes, useLocation } from "react-router-dom";
// Route : 특정 경로(path)에 대해 어떤 컴포넌트를 렌더링할지 정의하는 역할
// Routes: 여러 개의 Route를 감싸는 컨테이너 역할

import IndexPage from "./pages/IndexPage/IndexPage";
import WritePage from "./pages/WritePage/WritePage";
import ListPage from "./pages/ListPage/ListPage";
import MainLayout from "./components/MainLayout/MainLayout";
import SignupPage from "./pages/SignupPage/SignupPage";
import SigninPage from "./pages/SigninPage/SigninPage";
// 컴포넌트 분리

import { Global } from "@emotion/react";
// Global : 전역 스타일을 설정하는데 사용하는 컴포넌트 - 애플리케이션 전체 css

import { global } from "./styles/global";
import { useEffect, useState } from "react";
import axios from "axios";
import { useRecoilState } from "recoil";
import { authUserIdAtomState } from "./atoms/authAtom";
import { useQuery } from "react-query";
// Global을 뒤덮을 새로운 css. 여기 스타일이 전체 어플링케이션에 정의됨





function App() {
  const location = useLocation();

  // useQuery(1,2,3):
  // 1: Query 키 (Query의 식별자로 사용됨)
  // 2: Query를 통해 호출할 함수 (비동기 데이터 fetch 함수) - 지금 하는건 return이 axios로 연결됨
  // 3: 옵션 객체 (성공, 실패 핸들러와 조건부 실행 등 설정 가능)
  // useQuery는 렌더링 후 비동기 데이터를 가져오기 위해 호출된다.
  const authenticatedUserQuery = useQuery(
    ["authenticatedUserQuery"], 
    authenticatedUser,
    {    
      refetchOnWindowFocus: false,
      // 윈도우가 포커스를 받을 때 데이터를 다시 요청하지 않음
      enabled: !!localStorage.getItem("AccessToken"),
      // 조건부 실행: AccessToken이 있을 때만 Query 실행
    }
  );
  
  // 함수가 async로 선언되어 있고, 내부에서 await 키워드를 사용하여 비동기 요청이 완료될 때까지 기다림
  // 응답이 성공적으로 반환되면 Promise를 반환
  const authenticatedUser = async () => {
    return await axios.get("http://localhost:8080/servlet_study_war/api/authenticated", { // 지정된 URL로 GET 요청을 보냄
      headers: { // 요청할 때 헤더에 Authorization 토큰을 포함하여 인증 정보를 전달
        "Authorization": `Bearer ${localStorage.getItem("AccessToken")}`,
      } // Bearer 형식으로 토큰 저장 후 전달
    });
  }
  
  
  return (
    <>
      <Global styles={global} /> 

      {
        authenticatedUserQuery.isLoading ? <></> // authenticatedUserQuery가 로딩중이면 빈 화면 아니면 화면
        :
        <MainLayout>
          <Routes>
            <Route path="/" element={ <IndexPage /> } />
            <Route path="/write" element={ <WritePage /> } />
            <Route path="/list" element={ <ListPage /> } />
            <Route path="/signup" element={ <SignupPage /> } />
            <Route path="/signin" element={ <SigninPage /> } />
          </Routes>
        </MainLayout>
      }

    </>
    // MainLayout : 모든 경로에 공통으로 적용되는 구조
    // Route path="/" : 해당 경로와 일치하는 경우 오른쪽 컴포넌트를 렌더링
    // element={ < /> } : <Route> 컴포넌트에서 렌더링할 JSX 요소를 지정
  );
}

export default App;