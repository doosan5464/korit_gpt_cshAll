import { atom } from "recoil"; // atom을 사용해 전역 상태를 만듦

export const mainSidebarIsOpenState = atom({
    key: "mainSidebarIsOpenState",
    default: false,
});