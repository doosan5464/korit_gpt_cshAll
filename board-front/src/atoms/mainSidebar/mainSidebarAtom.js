import { atom } from "recoil";

// 사이드가 열려있는지 닫혀있는지
export const mainSidebarIsOpenState = atom({ // atom : Recoil의 상태 관리를 하는 기본 단위, 
                                             // 전역적이며 수정해도 알아서 업데이트하여 다른 컴포넌트애서도 업데이트 됨
    key: "mainSidebarIsOpenState",
    default: false,                          // 기본값은 사이드바가 닫혀 있는 상태
});