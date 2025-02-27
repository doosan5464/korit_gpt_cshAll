/**@jsxImportSource @emotion/react */
import { BiError } from 'react-icons/bi';
import * as s from './style';
import React from 'react';

function NotFoundPage(props) {
    return (
        <div css={s.layout}>
            <BiError />
            <h1>Not Found Page</h1>
            <p>This is not the webpage you are looking for.</p>
        </div>
    );
}

export default NotFoundPage;