import { css } from "@emotion/react";

export const global = css`

    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap');

    * {
        color: #333333;
    }

    html, body, #root {
        margin: 0;
        padding: 0;
        height: 100vh;
        overflow: auto;

        font-family: "Noto Sans KR", serif;
        font-size: 62.5%;   /** 1rem을 10px로 변환 */
    }

    #root {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #fafafa;
    }




`;