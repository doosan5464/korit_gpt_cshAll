import React, { useState } from 'react';  // 이 줄 추가


function Signin({userList}) {

    const [ signinInputValue, setSigninInputValue ] = useState({
        bookname: "",
        isbn: "",
    });

    const handleSigninInputOnChange = (e) => {
        setSigninInputValue({
            ...signinInputValue,
            [e.target.name]: e.target.value,
        });

        setTempNum(9999);
    };


    const [tempNum, setTempNum] = useState(9999);


    const handleSigninButtonOnClick = () => {
        let num = 0;
        let check = false;
        let finalNum = 0;
        userList.forEach(element => {
            if(element.username === signinInputValue.username) {
                check = true;
                finalNum = num;
            } else {
                num++;
            }
        });
        if(check) {
            setTempNum(finalNum);
            return alert(`${userList[tempNum].bookname}(${userList[tempNum].isbn})을 찾았습니다`);
        } else {
            return alert("사용자 정보를 다시 입력하세요.");        
        }
    };
    

    return (
        <div>
            <h1>조회</h1>
            <input type="text" name='bookname' placeholder='bookname' onChange={handleSigninInputOnChange} value={signinInputValue.bookname} />
            <div>
                <button onClick={handleSigninButtonOnClick}>조회</button>
            {
                tempNum !== 9999 && 
                    <table>
                        <thead>

                        </thead>
                            
                        <tbody>
                            <tr>
                                <td>{userList[tempNum].bookname}</td>
                                <td>{userList[tempNum].isbn}</td>
                                <td>{userList[tempNum].name}</td>
                                <td>{userList[tempNum].publisher}</td>
                                <td>{userList[tempNum].category}</td>
                            </tr>
                        </tbody>
                    </table> 
            }
            </div>


        </div>
    );
}

export default Signin;