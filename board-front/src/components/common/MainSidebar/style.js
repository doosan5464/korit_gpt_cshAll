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
    position: absolute;
    right: 0;
    box-sizing: border-box;
    padding: 0.6rem;
    width: 30rem;
    height: 100%;
`;

export const groupLayout = css`
    box-sizing: border-box;
    border-radius: 0.7rem;
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

export const profileImgBox = css`
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 1rem;
    border-radius: 0.5rem;
    width: 3rem;
    height: 3rem;
    background-color: #ffffff;
    overflow: hidden;

    & > img {
        width: 100%;
    }
`;

export const authText = css`
    display: inline-flex;
    align-items: center;

    & > svg {
        margin-right: 0.5rem;
    }
`;