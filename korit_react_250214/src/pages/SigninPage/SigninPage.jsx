import { Box, Button, Card, CardContent, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { api } from '../../api/config/axiosConfig';

/*
로그인 요구사항
각 필드가 공백인지만 체크(공백이면 아래 오류 메세지로)
로그인 버튼 클릭시 /api/auth/signin 요청 
-> 응답받은 accessToken을 localstorage에 AccessToken이라는 키값으로 저장
Index 페이지로 이동.
*/

function SigninPage(props) {

    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: "",
    });

    const [ errors, setErrors ] = useState({
        username: "",
        password: "",
    });

    const handleSigninInputOnChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name] : e.target.value,
        });
    }

    const handleSigninButtonOnClick = async () => {
        if(Object.entries(errors).filter(entry => !!entry[1]) > 0) { 
            return;
        }
        try {
            await api.post("/api/auth/signin", signinInput); 
            alert("로그인 완료.");
            // localStorage.setItem(api.response.body)
            // console.log(api.get("AccessToken"));
        } catch (error) { 
            setErrors({
                username: error.response.data.data,
                password: "",
            })
        }
    }

    const handleInputOnBlur = (e) => {
        const { name, value } = e.target;
        let message = "";
        if(name === "username" && !(/[\s]/.test(value))) {
            message = "공백이 포함됨;";
        }
        if(name === "password" && !(/[\s]/.test(value))) {
            message = "공백이 포함됨;";
        }

        setErrors({
            ...errors,
            [name]: message
        });
    }

    // 스타일을 쉽고 미리 만들어놓은 사이트로 만듦
    return (
        <div >
            <Card variant='outlined'>
                <CardContent>
                    <Typography variant='h4' textAlign={'center'}>로그인</Typography>
                    <Box display={"flex"} flexDirection={'column'} gap={2}>
                        <TextField type='text' label="username" name= 'username' 
                        onChange={handleSigninInputOnChange} value={signinInput.username}
                        obBluer={handleInputOnBlur}
                        error={!!errors.username}
                        helperText={errors.username} 
                        />
                        <TextField type='password' label="password" name= 'password' 
                        onChange={handleSigninInputOnChange} value={signinInput.password}
                        obBluer={handleInputOnBlur}
                        error={!!errors.password}
                        helperText={errors.password}
                        />
                        <Button variant='contained' onClick={handleSigninButtonOnClick}>로그인</Button>
                    </Box>
                </CardContent>
            </Card>
        </div>
    );
}

export default SigninPage;