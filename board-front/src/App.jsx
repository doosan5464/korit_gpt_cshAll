import { Global } from "@emotion/react"
import { global } from "./styles/global"
import MainLayout from "./components/common/MainLayout/MainLayout"
import MainContainer from "./components/common/MainContainer/MainContainer"
import MainSidebar from "./components/common/MainSidebar/MainSidebar"
import LoginPage from "./pages/LoginPage/LoginPage"
import { Route, Routes } from "react-router-dom"
import JoinPage from "./pages/JoinPage/JoinPage"

function App() {

	return (
		// Global styles={global} : 전역 스타일 적용
    	<>
			<Global styles={global} /> 
			<MainLayout>
				<MainSidebar />
				<MainContainer>
					<Routes>
						<Route path="/auth/login" element={<LoginPage />} />
						<Route path="/auth/join" element={<JoinPage />} />
					</Routes>
		
				</MainContainer>
			</MainLayout>
    	</>
		// MainLayout -> { children } 으로 자식들 렌더링
		// MainContainer -> { children } 으로 자식들 렌더링
		// ---> 페이지는 바꾸되 기본적으로 유지할 레이아웃을 설정한 것
		// Routes : 라우팅 설정 (페이지 관리)
  	)
}

export default App