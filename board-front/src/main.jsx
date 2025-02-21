import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { RecoilRoot } from 'recoil'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')).render( 
// createRoot(document.getElementById('root')) : React 18의 새로운 렌더링 방식 사용.
  <StrictMode> 
    <RecoilRoot>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </RecoilRoot>
  </StrictMode>,
  // StrictMode : React 개발 모드에서 잠재적 문제 감지
  // RecoilRoot : Recoil을 전역 상태 관리 라이브러리로 사용
  // BrowserRouter : React Router로 페이지 라우팅을 관리 -> App.jsx
)