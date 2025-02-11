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
import axios from "axios";
import { useRecoilState } from "recoil";
import { accessTokenAtomState } from "./atoms/authAtom";
import { useQuery } from "react-query";
// Global을 뒤덮을 새로운 css. 여기 스타일이 전체 어플링케이션에 정의됨




function App() {

  // useRecoilState()
  // : atom(authAtom.js)을 기반으로 상태를 읽고 수정할 수 있게 해주고, 상태를 애플리케이션 전체에서 공유할 수 있음
  // accessTokenAtomState는 atom의 키
  const [ accessToken, setAccessToken ] = useRecoilState(accessTokenAtomState);
  

  // 함수가 async로 선언되어 있고, 내부에서 await 키워드를 사용하여 비동기 요청이 완료될 때까지 기다림
  // 응답이 성공적으로 반환되면 Promise를 반환
  // Promise vs async
  // Promise는 반드시 1번 처음 호출
  // async는 호출이 될 때마다 Promise를 반환
  const authenticatedUser = async () => {
    return await axios.get("http://localhost:8080/servlet_study_war/api/authenticated", { // 지정된 URL로 GET 요청을 보냄
      // 여기서 await 뒤에 axios는 서버 호출이 성공하면 resolve, 실패하면 reject를 반환한다
      headers: { // 요청할 때 헤더에 Authorization 토큰을 포함하여 인증 정보를 전달
        "Authorization": `Bearer ${localStorage.getItem("AccessToken")}`, // Signin 에서 로그인하면서 로컬스토리지에 넣은 AccessToken
      } // Bearer 형식으로 토큰 저장 후 전달
    });
  }
  // -> 지금 이 함수가 밑에 useQuery보다 위에 위치해야 함


  // useQuery(1,2,3):
  // 1: Query 키 (Query의 식별자로 사용됨)
  // 2: Query를 통해 호출할 함수 (비동기 데이터 fetch 함수) - 지금 하는건 return이 axios로 연결됨
  // 3: 옵션 객체 (성공, 실패 핸들러와 조건부 실행 등 설정 가능)
  // useQuery : 처음 컴포넌트가 렌더링될 때 무조건 실행
  // useQuery : 렌더링 후 비동기 데이터를 가져오기 위해 호출된다.
  const authenticatedUserQuery = useQuery( // authenticatedUserQuery를 useQuery에 저장한 상태임.
    ["authenticatedUserQuery"], // 보통 함수명을 그대로 키 이름으로
    authenticatedUser,
    {    
      retry: 0, // React Query에서 요청이 실패했을 때 자동으로 재시도할 횟수를 설정하는 옵션
      refetchOnWindowFocus: false, // 윈도우가 포커스를 받을 때 데이터를 다시 요청하지 않음
      enabled: !!accessToken,
    }
  );


  return (
    <>
      <Global styles={global} /> 
      {
        authenticatedUserQuery.isLoading ? <></> // authenticatedUserQuery가 로딩중이면 빈 화면 아니면 화면
                                                 // 로딩중이라는게 함수가 아직 실행중이다? (쿼리의 결과를 아직 받지 못한 상태)
                                                 // authenticatedUserQuery는 동기 함수로 호출되는 비동기 함수
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