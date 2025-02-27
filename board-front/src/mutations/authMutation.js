import { useMutation } from "@tanstack/react-query";
import { joinApi, loginApi } from "../apis/authApi";

// 커스텀 훅
/* 
useMutation()
: 비동기 데이터 변경(POST, PUT, DELETE)**을 다룰 때 사용하는 React Query의 Hook 함수
  수동 실행 (mutate() 호출)
*/
export const useJoinMutation = () => useMutation({
  mutationKey: ["joinMutation"],
  mutationFn: joinApi,
  retry: 0,
});

export const useLoginMutation = () => useMutation({
  mutationKey: ["loginMutation"],
  mutationFn: loginApi,
  retry: 0,
});