
import { healthCheckApi } from "./api/apis/healthCheckApi";
import { Route, Routes } from "react-router-dom";
import IndexPage from "./pages/IndexPage/IndexPage";
import { Container } from "@mui/material";
import AuthRoute from "./routes/AuthRoute/AuthRoute";
import { userApi } from "./api/apis/userApi";
import { jwtDecode } from "jwt-decode";
import UserRoute from "./routes/UserRoute/UserRoute";
import { useQuery } from "@tanstack/react-query";
import MainHeader from "./components/MainHeader/MainHeader";

function App() {
	// const healthCheckQuery = useQuery({ // react-quert - 최신버전이 되면서 형식이 달라짐
	// 	queryKey: ["healthCheckQuery"], // queryKey : 함수명이랑 같은 쿼리키를 지정 
	// 	queryFn: healthCheckApi, // queryFn : 사용할 함수를 지정
	// 	cacheTime: 1000 * 60 * 10, // 캐시 유지 시간(언마운트 이후)
	// 	staleTime: 1000 * 60 * 10, // 10분마다 최신의 캐시 상태 유지(refetch)
	// });

	// if(!healthCheckQuery.isLoading) {
	// 	console.log(healthCheckQuery.data.data.status);
	// }

	const userQuery = useQuery({
		queryKey: ["userQuery"],
		queryFn: async () => { // promise 람다식
			const accessToken = localStorage.getItem("AccessToken"); // 백엔드에서 받아온 AccessToken
			if (!accessToken) {
				return null; // 원래는 그냥 return으로 함수를 종료시키지만 최신버전에서는 그게 안됨
			}
			const decodedJwt = jwtDecode(accessToken); // jwtDecode : 디코딩 (안에 claims 추출 가능)
			return await userApi(decodedJwt.jti); // await으로 비동기 처리, userId로 안되어있고 jti로 되어있어서
		},
	});

  	return (
    	<Container maxWidth="lg">
			{
				(!userQuery.isLoading && !userQuery.isRefetching) && // userQuery의 로딩이 끝나고 최신화가 끝난상태라면
				<>
					<MainHeader />
					<Routes>
						<Route path="/" element={<IndexPage />} />
						<Route path="/user/*" element={<UserRoute />} />
						<Route path="/auth/*" element={<AuthRoute />} />
					</Routes>
				</>
			}
    	</Container>
  	);
}

export default App;