import { useState } from "react"

// 커스텀 Hook 함수
export const useInputValid = ({regexp, errorText}) => { // 정규식
    const [name, setName] = useState("");
    const [value, setValue] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleOnChange = (e) => { // input 필드의 값이 변경될 때마다 호출되는 함수
        setName(e.target.name);
        setValue(e.target.value);
    }

    const handleOnBlur = () => { // 사용자가 입력 필드에서 벗어날 때 발생하는 이벤트
        const text = regexp.test(value) ? "" : errorText;
        setErrorMessage(text);
    }

    return { name, value, errorMessage, handleOnBlur, handleOnChange }; // 객체로 리턴 -> 변수명이랑 키값이 같으면 ??가 같다?
    // 변수명과 객체의 키값이 같으면 축약된 객체 리터럴(Shorthand Property Names) 문법이 적용
}