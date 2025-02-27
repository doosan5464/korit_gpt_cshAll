import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { RecoilRoot } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import Modal from "react-modal";

/*
react-modal
: React에서 모달 창을 쉽게 구현할 수 있게 해주는 라이브러리
  특정 이벤트(버튼 클릭 등)가 발생했을 때 뜨는 팝업 창
  setOpen(true, false) 로 관리
*/
Modal.setAppElement("#root"); // 모달이 열릴 때 백그라운드 요소들을 비활성화


const queryClient = new QueryClient({ // QueryClient의 설정을 정의
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
  <RecoilRoot> {/* Recoil의 상태는 atom과 selector를 통해 관리 */}
    <QueryClientProvider client={queryClient}> {/* queryClient 객체를 제공 */}
      <BrowserRouter> {/* BrowserRouter : React Router의 라우팅 기능을 활성화하는 컴포넌트 */}
        <App />
      </BrowserRouter>
    </QueryClientProvider>
  </RecoilRoot>
)
