import { api } from "../configs/axiosConfig"; // Axios 설정이 포함된 api 인스턴스 가져옴

// 회원가입 API 요청 함수
export const joinApi = async (joinInfo) => {
    return await api.post("/api/auth/join", joinInfo); 
    // joinInfo 데이터를 "/api/auth/join" 엔드포인트로 POST 요청
}

// 로그인 API 요청 함수
export const loginApi = async (loginInfo) => {
    return await api.post("/api/auth/login", loginInfo);
    // loginInfo 데이터를 ~~~
}