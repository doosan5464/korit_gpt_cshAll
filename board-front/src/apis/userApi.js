import { api } from "../configs/axiosConfig";

// 현재 로그인한 사용자 정보를 가져오는 API 함수
export const getUserMeApi = async () => {
    // '/api/user/me' 엔드포인트에 GET 요청을 보내서 사용자 정보를 가져옴.(백엔드)
    await api.get("/api/user/me");
}


// 프로필 이미지를 업데이트하는 API 함수
export const updateProfileImgApi = async (formData) => {
    return await api.post(
        "/api/user/profile/img", // 이미지 업로드를 위한 API 엔드포인트
        formData,                // FormData 객체에 담긴 이미지 파일
        {
            headers: {
                "Content-Type": "multipart/form-data",
                // 업로드할 데이터가 multipart/form-data 형식임을 명시
            },
        }
    );
}

// 닉네임 업데이트
export const updateNicknameApi = async (nickname) => await api.put("/api/user/profile/nickname", {nickname});

// 비밀번호 업데이트
export const updatePasswordApi = async (password) => await api.put("/api/user/profile/password", {password});