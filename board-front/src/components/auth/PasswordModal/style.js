import { css } from "@emotion/react";

export const modalTop = css`
    display: flex;
    justify-content: flex-end;
    font-size: 2rem;
    & > div {
        cursor: pointer;
        & > svg {
            fill: #bbbbbb;
            &:hover {
                fill: #999999;
            }
        }
    }
`;

export const header = css`
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 1rem 5rem;
`;

export const headerIcon = css`
    font-size: 3rem;
    & path {
        fill: #999999;
    }
`;

export const headerTitle = css`
    margin: 0.5rem;
    font-size: 1.6rem;
`;

export const headerMessage = css`
    text-align: center;
`;

export const inputGroup = css`
    display: flex;
    flex-direction: column;
    margin-bottom: 1rem;

    & > label {
        margin-bottom: 0.5rem;
        font-size: 1.2rem;
    }

    & > input {
        box-sizing: border-box;
        outline: none;
        border: 0.1rem solid #dbdbdb;
        border-radius: 0.5rem;
        padding: 0.5rem 1rem;
        font-size: 1.4rem;
        color: #666666;
        background-color: #fafafa;
    }
`;

export const setButton = css`
    box-sizing: border-box;
    margin-top: 0.5rem;
    border: none;
    border-radius: 0.5rem;
    padding: 0.8rem 2rem;
    width: 100%;
    background-color: #2383e2;
    color: #ffffff;
    cursor: pointer;

    &:active {
        background-color: #1b65af;
    }

    &:disabled {
        background-color: #eeeeee;
        cursor: default;
    }
`;