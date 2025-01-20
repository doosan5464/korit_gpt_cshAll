import React, { useState } from 'react';

function App13(props) {
    // 버튼을 클릭할 때 토큰을 만들기 전 단계. value의 username을 find해서 조건에 맞게함
    const getUser = (username) => {
        const userList = [
            {
                username: "aaa",
                name: "김준일",
            },
            {
                username: "bbb",
                name: "김준이",
            },
            {
                username: "ccc",
                name: "김준삼",
            },
        ];

        return new Promise((resolve, reject) => {
            const foundUser = userList.find(user => user.username === username);
            if(!!foundUser) {
                resolve(foundUser);
            } else {
                reject(new Error("사용자를 찾지 못함."));
            }
        });
    }

    // 조건에 맞는 user를 받아오면 토큰 생성 아니면 실패
    const generateToken = (user) => {
        return new Promise((resolve, reject) => {
            if(!!user) {
                resolve("새로만든 토큰!");
            } else {
                reject(new Error("토큰 생성 실패!"));
            }
        });
    }

    const generateToken2 = async (user) => {
        if(!user) {
            throw new Error("토큰 생성 실패!")
        }
        return "새로만든 토큰~~~";
    }


    const [ username, setUsername ] = useState();

    const handleUsernameOnChange = (e) => {
        setUsername(e.target.value);
    } 

    const handleButtonOnClick = () => {

        getUser(username) 
        .then(user => {
            generateToken(user)
            .then(token => console.log(token));
        })
        .catch(error => console.error(error));
    }

    // 기본 함수앞에 async, await 적용
    // 위에꺼랑 바로 밑에꺼랑 이거랑 3개다 똑같음
    // async function handleButtonOnClick2() { 
    //     const result = await getUser(username);
    //     const token = await handleButtonOnClick2(result);
    // }

    // 익명 함수(변수에 함수를 담았을 뿐). 함수() 앞에 async
    const handleButtonOnClick2 = async () => { // async : 비동기로 정의
       try {
           const result = await getUser(username); // await(a wait -> then의 예약을 기다린다) Promise가 처리될 때까지 대기하고 resolve된 값을 반환
           console.log(result);
           const token = await generateToken2(result); // await을 쓰면 then 메서드 없이도 비동기 결과를 바로 사용할 수 있음
           console.log(token);
       } catch(error) { // reject는 try의 catch로 넘긴다
           console.error(error);
       }


    }

    return (
        <div>
            <input type="text" value={username} onChange={handleUsernameOnChange} />
            <button onClick={handleButtonOnClick}>토큰 생성</button>
            <button onClick={handleButtonOnClick2}>토큰 생성2</button>
        </div>
    );
}

export default App13;