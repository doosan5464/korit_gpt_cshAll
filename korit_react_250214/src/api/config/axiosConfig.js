import axios from "axios";

export const api = axios.create({
    baseURL: "http://localhost:8080", 
    // 기본 요청 URL을 http://localhost:8080으로 설정 → 이후 api.get("/server/hc") 같은 요청은 http://localhost:8080/server/hc로 변환됨
    headers: {
        Authorization: !!localStorage.getItem("AccessToken") && `Bearer ${localStorage.getItem("AccessToken")}`,
        // 로컬 스토리지에서 "AccessToken" 값을 가져와, 있으면 "Bearer <토큰>" 형식으로 Authorization 헤더에 추가
    }, 
});






