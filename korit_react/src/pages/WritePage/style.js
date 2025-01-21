import { css } from "@emotion/react";

export const headerLayout = css`
    display: flex;
    justify-content: flex-end;
    margin-bottom: 10px;
    width: 100%;

    & > button {
        box-sizing: border-box;
        border: 1px solid #dbdbdb;
        padding: 10px 20px;
        background-color: #ffffff;
        font-weight: 600;
        cursor: pointer;

        &:hover {
            background-color: #fafafa;
        }

        &:active {
            background-color: #eeeeee;
        }
    }
`;

export const titleLayout = css`
width: 100%;

    & > input {
        box-sizing: border-box;
        margin-bottom: 5px;
        outline: none;
        border: 1px solid #dbdbdb;
        padding: 12px 15px;
        width: 100%;
        height: 40px;
    }
`;