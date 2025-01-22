import { Route, Routes } from "react-router-dom";
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
// Global을 뒤덮을 새로운 css. 여기 스타일이 전체 어플링케이션에 정의됨


function App() {
  
  return (
    <>
      <Global styles={global} /> 

      <MainLayout>
        <Routes>
          <Route path="/" element={ <IndexPage /> } />
          <Route path="/write" element={ <WritePage /> } />
          <Route path="/list" element={ <ListPage /> } />
          <Route path="/signup" element={ <SignupPage /> } />
          <Route path="/signin" element={ <SigninPage /> } />

        </Routes>
      </MainLayout>
    </>
    // MainLayout : html, css담당. 어느 페이지를 가든 이건 고정
    // Route path="/" : 이 경로면 오른쪽 실행
    // element={ < /> } : <Route> 컴포넌트에서 어떤 컴포넌트를 렌더링할지 지정하는 속성
  );
}

export default App;