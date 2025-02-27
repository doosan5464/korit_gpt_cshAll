import axios from "axios";

export const api = axios.create({
    baseURL: "http://localhost:8080",
});

// 요청때 사용되어지면 인터셉트.
// 요청을 보내기 전에 특정 동작을 수행할 수 있음 (ex: 토큰 추가, 헤더 수정, 로깅 등)
api.interceptors.request.use(config => {
    const accessToken = localStorage.getItem("AccessToken");
    config.headers.Authorization = accessToken && `Bearer ${localStorage.getItem("AccessToken")}`; // 모든 요청에 AccessToken이 달리게 된다
    return config;
});

// 로컬스토리지에 AccessToken 저장
export const setTokenLocalStorage = (name, token) => {
    if (!!token) {
        localStorage.setItem(name, token);
    } else {
        localStorage.removeItem(name);
    } 
}