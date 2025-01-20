import React, { useState } from 'react';

function Signup({userList, setUserList}) { //
        const [ signupInputValue, setSignupInputValue ] = useState({
            bookname: "",
            isbn: "",
            name: "",
            publisher: "",
            category: "",
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
    
            alert("등록완료.");
    
            setSignupInputValue({
                bookname: "",
                isbn: "",
                name: "",
                publisher: "",
                category: "",
            });
        };
        console.log(userList);

    return (
        <div>
            <h1>등록</h1>
            <input type="text" name='bookname' placeholder='bookname'       onChange={handleSignupInputOnChange} value={signupInputValue.bookname} />
            <input type="password" name='isbn' placeholder='isbn'   onChange={handleSignupInputOnChange} value={signupInputValue.isbn} />
            <input type="text" name='name' placeholder='name'               onChange={handleSignupInputOnChange} value={signupInputValue.name} />
            <input type="text" name='publisher' placeholder='publisher'             onChange={handleSignupInputOnChange} value={signupInputValue.publisher} />
            <input type="text" name='category' placeholder='category'             onChange={handleSignupInputOnChange} value={signupInputValue.category} />
            <div>
                <button onClick={handleSignupButtonOnClick}>등록</button>
            </div>
        </div>
    );
}

export default Signup;