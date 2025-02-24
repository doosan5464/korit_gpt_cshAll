/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useState } from 'react';

function ValidInput({
    type, 
    name, 
    placeholder, 
    value, 
    onChange,
    onFocus = null,
    regexp, 
    errorMessage,
    inputValidError,
    setInputValidError
}) {
    
    const handleOnBlur = () => {
        setInputValidError(prev => ({
            ...prev,
            [name]: !regexp.test(value),
        }));
    }

    return (
        <div css={s.groupBox}>
            <input css={s.textInput} 
                type={type} 
                name={name}
                placeholder={placeholder}
                value={value}
                onChange={onChange} 
                onFocus={onFocus}
                onBlur={handleOnBlur}
            />
            {
                !!inputValidError[name] &&
                <p css={s.messageText}>{errorMessage}</p>
            }
        </div>
    );
}

export default ValidInput;