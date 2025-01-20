// 
// 

import "./style.css"; // 같은 폴더 css 적용

function Hello2() { 
    const h1Text = 'Hello React!!';
    const h1 = <h1>{h1Text}</h1> // 변수에 변수 넣기 가능
    
    return <>
        {h1}
        <label className="box" htmlFor="username">아이디</label>
        <input type="text"/>
    </>
}
export default Hello2; // 다른 곳에서 import 허용





export function printConsol() { // 앞에 대문자가 아니라 소문자라 그냥 함수(컴포넌트 X)
    console.log("hello2파일 입니다.");
}

export function printConsol2() { 
    console.log("hello2파일 입니다.");
}

