import { css } from "@emotion/react";

export const container = css`
    padding: 0 4rem;
`;

export const title = css`
    box-sizing: border-box;
    margin: 0;
    margin-bottom: 2rem;
    border-bottom: 0.1rem solid #dbdbdb;
    padding: 1rem 0 2rem;
    font-size: 2rem;
`;

export const accountBox = css`
    display: flex;
    align-items: center;
    margin-bottom: 5rem;
`;

export const profileImgBox = css`
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
    border-radius: 50%;
    width: 10rem;
    height: 10rem;
    overflow: hidden;
    cursor: pointer;

    & > img {
        width: 100%;
    }

    & > input[type="file"] {
        display: none;
    }
`;

export const nicknameTitle = css`
    margin: 0 0 0.7rem;
    font-size: 1.4rem;
    font-weight: 400;
    cursor: default;
`;

export const textInput = css`
    box-sizing: border-box;
    outline: none;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 1rem 1.5rem;
    width: 30rem;
`;

export const saveButton = css`
    box-sizing: border-box;
    margin-top: 0.5rem;
    border: none;
    border-radius: 0.5rem;
    padding: 0.7rem 2rem;
    background-color: #2383e2;
    color: #ffffff;
    cursor: pointer;

    &:active {
        background-color: #1b65af;
    }
`;

export const itemGroup = css`
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 2rem;
`;

export const subTitle = css`
    margin: 0;
    font-size: 1.6rem;
    font-weight: 400;
`;

export const subContent = css`
    margin: 0.5rem 0;
    font-size: 1.4rem;
    font-weight: 400;
    color: #777777;
`;

export const borderButton = css`
    box-sizing: border-box;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 0.7rem 2rem;
    background-color: #ffffff;
    cursor: pointer;

    &:hover {
        background-color: #fafafa;
    }

    &:active {
        background-color: #eeeeee;
    }
`;