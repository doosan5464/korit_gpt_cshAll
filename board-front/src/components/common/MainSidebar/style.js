import { css } from "@emotion/react";

export const layout = (isOpen) => css`
    position: relative;
    transition: all 0.5s ease-in-out;
    box-sizing: border-box;

    width: ${isOpen ? "30rem" : "0rem"};
    height: 100%;

    background-color: #f8f8f5;
`;

export const container = css`
    position: absolute; // absolute로 글자 없애기(overflow:hidden이라 넘어가면 안보임)
    right: 0;
    box-sizing: border-box;
    padding: 0.6rem; // 맞는지 모르겠음
    width: 30rem;
    height: 100%;
`;

export const groupLayout = css`
    box-sizing: border-box;
    border-radius: 0.6rem;
    padding: 0.6rem;
    width: 100%;
    cursor: pointer;

    &:hover {
        background-color: #00000017;
    }
`;

export const topGroup = css`
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

export const user = css`
    display: flex;
    font-size: 1.6rem;
`;

export const authText = css`
    display: inline-flex; // icon 가운데 정렬하려고 flex들 전부 inline처리
    align-items: center;

    & > svg {
        margin-right: 0.5rem;
    }
`;