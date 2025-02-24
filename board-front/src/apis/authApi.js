import { api } from "../configs/axiosConfig";
// joinApi라는 비동기 함수로, 회원 가입을 처리하는 API 호출

export const joinApi = async (joinInfo) => {
    return await api.post("/api/auth/join", joinInfo);
    // joinInfo 객체를 받아서, api.post 메서드를 사용해 /api/auth/join 경로에 회원 가입 정보를 POST 방식으로 전송
}