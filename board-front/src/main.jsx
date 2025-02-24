import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { RecoilRoot } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1
    },
    mutations: {
      retry: 1
    }
  }
});

createRoot(document.getElementById('root')).render( 
// createRoot(document.getElementById('root')) : React 18의 새로운 렌더링 방식 사용.
  <StrictMode> 
    <RecoilRoot>
      <QueryClientProvider client={queryClient}> 
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </QueryClientProvider>
    </RecoilRoot>
  </StrictMode>,
  // StrictMode : React 개발 모드에서 잠재적 문제 감지
  // RecoilRoot : Recoil을 전역 상태 관리 라이브러리로 사용
  // QueryClientProvider : React 애플리케이션에서 React Query의 기능을 사용할 수 있도록 설정해주는 역할 - 원래 이렇게 한다고 함
  // BrowserRouter : React Router로 페이지 라우팅을 관리 -> App.jsx
)