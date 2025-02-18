import { api } from "../config/axiosConfig";

// userId -> userApi 함수의 매개변수로 전달되며, 해당 값을 이용해 API 요청을 보낸다
export const userApi = async (userId) => await api.get(`/api/user/${userId}`);