/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useState } from 'react';


// ValidInput 컴포넌트: 입력 필드의 유효성 검사를 처리하는 컴포넌트
// 컴포넌트
// - 입력 필드 제공 : 사용자에게 텍스트 필드를 제공하여 값을 입력받음.
// - 유효성 검사 : 입력값에 대해 (정규표현식)유효성 검사를 실행.
// - 오류 메시지 처리 : 유효성 검사에 실패한 경우 오류 메시지를 사용자에게 표시.
function ValidInput({
    type = "text", 
    name = "", 
    placeholder= "", 
    value, 
    onChange = null,
    onFocus = null,
    regexp = null, 
    errorMessage = "",
    inputValidError = null,
    setInputValidError = null
}) {
    
    const handleOnBlur = () => {
        if(!regexp) { 
            return; // regexp가 없으면 유효성 검사 수행하지 않음
        }

        // 유효성 검사 결과를 오류 상태에 반영
        setInputValidError(prev => ({
            ...prev, 
            [name]: !regexp.test(value), // 정규식에 맞지 않으면 오류로 설정
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
                !!inputValidError &&
                !!inputValidError[name] && // 유효성 검사 오류가 있으면
                <p css={s.messageText}>{errorMessage}</p> // 오류 메시지 표시
            }
        </div>
    );
}

export default ValidInput;