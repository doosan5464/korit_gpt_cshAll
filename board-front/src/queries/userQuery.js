import { useQuery } from "@tanstack/react-query";
import { getUserMeApi } from "../apis/userApi";

// 사용자 정보를 가져오는 커스텀 훅
/*
useQuery()
: React Query에서 데이터를 가져올 때(GET 요청, 조회) 사용하는 Hook 함수
  자동 실행 (enabled로 제어 가능)
*/
export const useUserMeQuery = () => useQuery({
    queryKey: ["userMeQuery"],      // 쿼리의 고유 키 (쿼리 캐시를 구분짓는 키)
    queryFn: getUserMeApi,          // 데이터를 가져오는 함수 (API 호출)
    retry: 0,                       // 실패 시 재시도 횟수 (0으로 설정하여 자동 재시도를 하지 않음)

    staleTime: 1000 * 60 * 20, // 데이터가 fresh한 시간
    // 데이터가 신선한 상태로 간주되는 시간 (20시간)

    gcTime: 1000 * 60 * 10,         // 상한 데이터를 지우는 시간
    // GC(Garbage Collection) 시간: 캐시에서 삭제되는 시간 (10분)
});
