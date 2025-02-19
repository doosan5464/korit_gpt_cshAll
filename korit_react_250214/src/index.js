import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { RecoilRoot } from 'recoil';
import { BrowserRouter } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const root = ReactDOM.createRoot(document.getElementById('root'));

const queryClient = new QueryClient({ // react-query 를 제일 상단에 선언, 저장
  defaultOptions: {
    queries: {
      retry: 0,
      refetchOnWindowFocus: false,
    }
  }
});

root.render(
  <QueryClientProvider client={queryClient} >
    <RecoilRoot>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </RecoilRoot>
  </QueryClientProvider>
);

reportWebVitals();