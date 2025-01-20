import React, { useEffect, useState } from 'react';

function App10(props) {
    const [ num1, setNum1 ] = useState(0);
    const [ num2, setNum2 ] = useState(0);

    const unmount = () => {
        console.log("장착해제됨");
    }

    const mount = () => {
        console.log("장착됨2");
        return unmount; // 언마운트는 잘 안씀
    }
    // seEffect는 무조건 1번은 실행
    // useEffect(mount, []); // 첫 번째 매개변수로 함수가 들어감, return도 함수가 들어감
    // 여기도 마찬가지로 ,[]가 의존성임. 
    useEffect(mount);

    useEffect(() => {
        console.log(num1);
        setNum2(num1 + 100);
    }, [num1]); // num1이 변하면 useEffect가 동작(호출)한다
    // num1이 의존성

    useEffect(() => {
        console.log(num2);
    }, [num2]);
    
    useEffect(() => {
        console.log(num2);
    }, [num1, num2]); // num1이나 num2 둘 중 하나라도 바뀌면 실행

    useEffect(() => {
        console.log("마운트!!!")
    }, []); 
    // 빈 배열 [] : useEffect는 무조건 1번은 실행하는데 이렇게 두면 빈 배열은 안바뀌니까 1번만 실행


    const handleOnClick = () => {       
        setNum1(num1 + 10); 
        // setNum2(num1 + 100); -> 비동기때문에 110이 차이나게 됨
    }

    console.log("???????");

    return (
        <div>
            <h1>Num1: {num1}</h1>        
            <h1>Num2: {num2}</h1>        

            <button onClick={handleOnClick}>클릭</button>
        </div>
    );
}

export default App10;
