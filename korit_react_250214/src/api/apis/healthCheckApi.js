import { api } from "../config/axiosConfig";

// healthCheckApi 함수는 "/server/hc" 엔드포인트로 GET 요청을 보내고, 
// 요청 결과(Promise 객체)를 반환한다.
export const healthCheckApi = async () => api.get("/server/hc");