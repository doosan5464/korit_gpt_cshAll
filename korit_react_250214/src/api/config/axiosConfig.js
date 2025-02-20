import axios from "axios";

export const api = axios.create({ // 기본 API 인스턴스 생성 (Base URL 설정)
    baseURL: "http://localhost:8080", // 백엔드 서버 주소 (InteliJ)
});

// .interceptors.request : 요청 인터셉터는 모든 HTTP 요청이 서버로 보내지기 전에 실행되는 함수
api.interceptors.request.use(config => { 
    // 즉 서버로 보내지기전에 요청을(config) 수정하고 return
    const token = localStorage.getItem("AccessToken");
    if (!!token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config; // 수정된 요청 설정 반환
}, error => Promise.reject(error)); // 요청 에러 발생 시 그대로 반환

// 로컬스토리지에 token 활성화 or 비활성화
export const setAccessToken = (token) => { 
    if (!!token) {
        localStorage.setItem("AccessToken", token);
    } else {
        localStorage.removeItem("AccessToken");
    }
};

// 마찬가지
export const setRefreshToken = (token) => {
    if (!!token) {
        localStorage.setItem("RefreshToken", token);
    } else {
        localStorage.removeItem("RefreshToken");
    }
};