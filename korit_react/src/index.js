import App from './App';

import { BrowserRouter } from 'react-router-dom';
// BrowserRouter : React Router에서 URL 경로를 감지하고 페이지 이동을 처리하는 컴포넌트

import ReactDOM from 'react-dom/client'; 
import { RecoilRoot } from 'recoil';
import { QueryClient, QueryClientProvider } from 'react-query';
// ReactDOM : React 애플리케이션을 실제로 DOM에 렌더링하는 역할을 하는 객체

const root = ReactDOM.createRoot(document.getElementById('root'));
// createRoot() : 컴포넌트를 DOM에 마운트할 수 있게 하고, 이걸 통해 앱을 실제 HTML 페이지에 렌더링


// 올바른 사용법
// BrowserRouter로 애플리케이션 전체를 감싸서 브라우저 기반의 라우팅을 사용할 수 있게 해주는 방식
// RecoilRoot안에 있는 authAtom을 통해 Recoil 상태를 전역으로 쓸 수 있음


// BrowserRouter : URL 변경 감지와 라우트 매칭을 처리하며, App 컴포넌트에 라우팅 기능 제공
// RecoilRoot : Recoil의 전역 상태 관리 제공 (authAtom을 통해 상태를 선언 및 공유)
// QueryClientProvider : client={queryClient}로 React Query의 QueryClient를 하위 컴포넌트에 전달


const queryClient = new QueryClient(); 
// queryClient: React Query에서 서버 상태를 관리하는 전역 상태 저장소
// 컴포넌트 트리 전체에서 데이터를 캐싱하고, 중복 요청을 방지


root.render(
    <RecoilRoot> 
        <QueryClientProvider client={queryClient}>
            <BrowserRouter>
                <App />
            </BrowserRouter>
        </QueryClientProvider>
    </RecoilRoot>
);