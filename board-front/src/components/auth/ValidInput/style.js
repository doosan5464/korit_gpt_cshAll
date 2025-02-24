import { css } from "@emotion/react";

export const groupBox = css`
    box-sizing: border-box;
    padding: 0.6rem 0;
    width: 32.6rem;
`;

export const textInput = css`
    box-sizing: border-box;
    outline: none;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 0.5rem 1rem;
    width: 100%;
    height: 3.4rem;
    background-color: transparent;

    font-size: 1.4rem;
    letter-spacing: 0.1rem;

    &:focus {
        box-shadow: 0rem 0rem 0.2rem 0.2rem #7edaff;
    }
`;

export const messageText = css`
    margin: 0;
    margin-top: 0.3rem;
    color: #ff3f3f;
    font-size: 1.2rem;
`;