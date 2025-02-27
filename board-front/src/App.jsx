import { Global } from "@emotion/react"
import { global } from "./styles/global"
import MainLayout from "./components/common/MainLayout/MainLayout"
import { Route, Routes } from "react-router-dom"
import AuthRoute from "./routes/AuthRoute/AuthRoute"
import { useUserMeQuery } from "./queries/userQuery"
import MainRoute from "./routes/MainRoute/MainRoute"

function App() {
	
	useUserMeQuery();

	return (
    	<>
			<Global styles={global} />
			<MainLayout>
				<Routes>
					<Route path="/auth/*" element={<AuthRoute />} />
					<Route path="/*" element={<MainRoute />} />
				</Routes>
			</MainLayout>
    	</>
  	)
}

export default App