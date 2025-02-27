import { css } from "@emotion/react";

export const layout = css`
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
`;

export const groupBox = css`
    box-sizing: border-box;
    padding: 0.6rem 0;
    width: 32.6rem;
`;

export const title1 = css`
    margin: 0.5rem 0;
    font-size: 2rem;
    cursor: default;
`;

export const title2 = css`
    margin: 0.5rem 0;
    font-size: 2rem;
    color: #bbbbbb;
    cursor: default;    
`;

export const accountMessage = css`
    font-size: 1.2rem;
    color: #777777;
    cursor: default;
`;

export const accountButton = css`
    box-sizing: border-box;
    border: none;
    border-radius: 0.5rem;
    width: 100%;
    height: 4rem;
    font-size: 1.4rem;
    letter-spacing: 0.1rem;
    color: #ffffff;
    background-color: #2383e2;
    cursor: pointer;
    &:active {
        background-color: #1b65af;
    }
`;

export const oauth2Group = css`
    box-sizing: border-box;
    margin-bottom: 1rem;
    border-bottom: 0.1rem solid #dbdbdb;
    padding-top: 2rem;
    padding-bottom: 1rem;
`;

export const oauth2Button = css`
    position: relative;
    display: flex;
    align-items: center;
    box-sizing: border-box;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 0.5rem 1rem;
    width: 100%;
    height: 3.6rem;
    background-color: transparent;
    cursor: pointer;
    
    &:active {
        background-color: #fafafa;
    }
`;

export const oauth2Icon = css`
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 2.4rem;
    height: 2.4rem;
`;

export const oauth2Text = css`
    flex-grow: 1;
    font-weight: 600;
`;

export const footerAgreement = css`
    display: inline-flex;
    justify-content: center;
    box-sizing: border-box;
    padding: 0.6rem 0;
    width: 32.6rem;
    font-size: 1.2rem;
    text-align: center;
`;