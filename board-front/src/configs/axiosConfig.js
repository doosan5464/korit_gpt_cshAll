import axios from "axios";

// axios.create() : 요청을 보낼 때마다 기본적으로 설정된 값들을 사용
export const api = axios.create({
    baseURL: "http://localhost:8080", // 코드에서 API 호출을 할 때마다 전체 URL을 다시 쓸 필요가 없어짐
});