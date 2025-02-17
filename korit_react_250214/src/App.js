import axios from "axios";
import { useQuery } from "react-query";
import { api } from "./api/config/axiosConfig";
import { healthCheckApi } from "./api/apis/healthCheckApi";
import { Route, Routes } from "react-router-dom";
import IndexPage from "./pages/IndexPage/IndexPage";
import SignupPage from "./pages/SignupPage/SignupPage";
import SigninPage from "./pages/SigninPage/SigninPage";
import ProfilePage from "./pages/ProfilePage/ProfilePage";
import { Container } from "@mui/material";

function App() { // 대문자니까 Component
  /*

  useQuery : 비동기 API 요청을 알아서 실행하고, 상태를 관리
           -> useQuery는 기본적으로 객체가 정해져 있음
           -> 서버로부터 받은 데이터를 관리함***
           -> 컴포넌트가 렌더링될 때 자동으로 데이터를 요청

  const {
    data,         // 요청 결과 데이터 (성공 시)
    isLoading,    // 로딩 중이면 true
    isError,      // 에러 발생 시 true
    error,        // 에러 객체 (에러 발생 시)
    isSuccess,    // 요청 성공 시 true
    refetch,      // 강제로 다시 요청할 수 있는 함수
    status,       // 'loading', 'error', 'success' 상태 값
  } = useQuery(["healthCheckQuery"], healthCheckApi);

  */
  const healthCheckQuery = useQuery( 
    ["healthCheckQuery"], 
    healthCheckApi, // 요청을 보낼 API 함수(비동기 함수 필요, async 대신에 사용)
                    // useQuery는 두 번째 인자로 전달된 함수(여기서는 healthCheckApi)를 비동기 함수로 처리해서 API 요청을 자동으로 처리(즉, 2번째 인자는 반드시 비동기 함수여야 한다.promise)
    {
      refetchOnWindowFocus: false, // 윈도우(브라우저) 포커스를 받을 때 자동으로 refetch 안 함.
      enabled: true, // true면 컴포넌트 마운트 시 자동 실행, false면 수동 실행 필요.
      cacheTime: 1000 * 60 * 10, // 캐시 유지 시간(언마운트 이후)
      staleTime: 1000 * 60 * 10, // 화면 유지시간(10분마다 최신의 캐시 상태 유지. refetch)
    }
  );

  if(!healthCheckQuery.isLoading) {
    console.log(healthCheckQuery.data.data.status); // ???
  }
  
  return (
    <Container maxWidth="lg">
      <Routes>
        <Route path="/" element={<IndexPage />}/>
        <Route path="/profile" element={<ProfilePage />}/>
        <Route path="/signup" element={<SignupPage />}/>
        <Route path="/signin" element={<SigninPage />}/>
      </Routes>
    </Container>
  );
}

export default App;