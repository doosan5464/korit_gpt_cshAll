import { useMutation } from "@tanstack/react-query";
import { joinApi } from "../apis/authApi";

// useJoinMutation : react-query의 useMutation을 사용하여 비동기 작업을 처리하는 데 사용
export const useJoinMutation = () => useMutation({
    mutationKey: ["joinMutation"],
    mutationFn: joinApi,
    retry: 0,
});