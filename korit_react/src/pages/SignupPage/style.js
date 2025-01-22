import { css } from "@emotion/react";

export const layout = css`
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 100px;
    width: 100%;
`;

export const main = css`
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
    border: 1px solid #dbdbdb;
    padding: 40px;
    width: 400px;

    & > input {
        box-sizing: border-box;
        margin-bottom: 10px;
        outline: none;
        border: 1px solid #dbdbdb;
        padding: 10px 20px;
    }

    & > button {
        box-sizing: border-box;
        margin-top: 20px;
        outline: none;
        border: 1px solid #dbdbdb;
        padding: 10px 20px;
        background-color: #444444;
        color: #fafafa;
        font-weight: 600;
        cursor: pointer;

        &:hover {
            background-color: #999999;
        }
        &:active {
            background-color: #777777;
        }
    }
`;
export const footer = css`
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    border: 1px solid #dbdbdb;
    padding: 40px;
    width: 400px;

    & > span {
        margin-right: 10px;
        cursor: default;
    }
    // Link -> a
    & > a {
        text-decoration: none;
        color: #d5ae00;
        font-weight: 600;
    }
`;