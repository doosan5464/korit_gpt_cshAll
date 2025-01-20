import React, { useState } from 'react';
/*
Promise -> async / await

*/
function App12(props) {
    
    const findUserByUsername = (username) => {
        const userList = [
            {username: "aaa", password: "1111"},
            {username: "bbb", password: "2222"},
            {username: "ccc", password: "3333"},
        ];
        return userList.find(user => user.username === username); // username이 같은 객체만 반환
    }

    const getUserApi = (url, params) => {

        return new Promise((resolve, reject) => {
            console.log(`서버에 ${url}?${params}요청!!`);
            const foundUser = findUserByUsername(params.username); // params 매개변수는 {username: 값} 형태로 들어와야 한다

            if(!!foundUser) {
                resolve(foundUser); 
            } else {
                reject(new Error("해당 사용자 정보를 찾을 수 없습니다.")); 
            }
        });
    }


    const [ usernameInput, setUsernameInput] = useState("");

    const handleUsernameInputOnChange = (e) => { // 실시간 인풋최신화
        setUsernameInput(e.target.value);
    }

    const handleSearchOnClick = () => {
        getUserApi("http://localhost:8080/user", {username: usernameInput}) // 밑의 value의 usernameInput 값이 params.username으로 전달됨
        // usernameInput은 위에 useState로 만든 상태변수이고 onChange:handleUsernameInputOnChange로 계속 값을 최신화하고 있기때문에
        // 위에 const foundUser = findUserByUsername(params.username) 에 전달하면 사용자의 입력값을 준 것이나 마찬가지다

        .then((response) => { // getUserApi는 return으로 Promise를 줘서 then, catch 사용 가능
            console.log(response);
        }).catch((error) => {
            console.error(error);
        })
    }

    return (
        <div>
            <input type="text" placeholder='username' value={usernameInput} onChange={handleUsernameInputOnChange} />
            <button onClick={handleSearchOnClick}>찾기</button>
        </div>
    );
}



export default App12;