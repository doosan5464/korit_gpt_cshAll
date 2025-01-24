import { atom } from "recoil";

export const accessTokenAtomState = atom({
    key: "AccessTokenAtomState",
    default: localStorage.getItem("AccessToken"),
});