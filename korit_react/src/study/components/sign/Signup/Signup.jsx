import React, { useState } from 'react';

function Signup({userList, setUserList}) { //
        const [ signupInputValue, setSignupInputValue ] = useState({
            username: "",
            password: "",
            name: "",
            email: "",
        });

        const handleSignupInputOnChange = (e) => {
            setSignupInputValue({
                ...signupInputValue,
                [e.target.name]: e.target.value,
            });
        };
    
        const handleSignupButtonOnClick = () => {
            setUserList([
                ...userList,
                signupInputValue,
            ]);
    
            alert("가입완료.");
    
            setSignupInputValue({
                username: "",
                password: "",
                name: "",
                email: "",
            });
        };


    return (
        <div>
            <h1>회원가입</h1>
            <input type="text" name='username' placeholder='username'       onChange={handleSignupInputOnChange} value={signupInputValue.username} />
            <input type="password" name='password' placeholder='password'   onChange={handleSignupInputOnChange} value={signupInputValue.password} />
            <input type="text" name='name' placeholder='name'               onChange={handleSignupInputOnChange} value={signupInputValue.name} />
            <input type="text" name='email' placeholder='email'             onChange={handleSignupInputOnChange} value={signupInputValue.email} />
            <div>
                <button onClick={handleSignupButtonOnClick}>가입하기</button>
            </div>
        </div>
    );
}

export default Signup;