import React, { useState } from 'react';
/*

비동기 : 
*/  
function App3(props) {
    const [name, setName] = useState("");
    const [nameInputText, setNamepInputText] = useState("");
    const [gender, setGender] = useState("");
    const [genderCheckedId, setGenderCheckedId] = useState("male");
    console.log(nameInputText); // 비동기 : 이렇게 빼면 되긴 하는데 재렌더링 할 때마다 사용됨
    // 상태가 변화되면 다시 이 컴포넌트가 호출되니까 바로 되는 것처럼 보이지만 실제로는 그러지 않음

    const handleNameInputOnChange = (e) => { // input태그의 위치를 가져오기 위해서 e
        setNamepInputText(e.target.value); // 바꾸라고 명령(시간이 조금 걸림)
        // console.log(nameInputText); // 위 코드와 동시에 코드 실행(다음 호출때 한 박자 느리게 출력)
    }
    const handleCheck = () => {
        setName(nameInputText);
        setNamepInputText("");
        setGender(genderCheckedId === "male" ? "남" : "여");
    }
    const handleGenderOnChange = (e) => {
        setGenderCheckedId(e.target.id);
    }

    // input 태그에 value={nameInputText} -> 여기도 해줘야 함.
    // input칸에 입력할 때 마다 상태가 변화되는데 input의 value값도 똑같이 해줘야 한다

    // htmlFor 는 id와 연결
    // name이 같아서 둘 중 하나만 체크 가능하게 됨

    return (
        <div>
            <h1>이름 : {name}({gender})</h1>
            <input type="text" onChange={handleNameInputOnChange} value={nameInputText}/> 
            <input type="radio" id="male" name="gender" onChange={handleGenderOnChange} checked={genderCheckedId === "male"} value={"male"} />
            <label htmlFor="male">남</label>
            <input type="radio" id="female" name="gender" onChange={handleGenderOnChange} checked={genderCheckedId === "female"} value={"female"} />
            <label htmlFor="female">여</label>
            <button onClick={handleCheck}>확인</button>
        </div>
    );
}

export default App3;