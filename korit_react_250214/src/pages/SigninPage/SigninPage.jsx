import { Box, Button, Card, CardContent, Container, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api, setAccessToken, setRefreshToken } from '../../api/config/axiosConfig';
import { useQueryClient } from '@tanstack/react-query';


function SigninPage(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: "",
    });

    const [ errors, setErrors ] = useState({
        username: "",
        password: "",
    });

    const [ isSigninError, setSigninError ] = useState(false);


    const handleInputOnBlur = (e) => { // 공백을 확인
        const { name, value } = e.target;
        setErrors(prev => ({
            ...prev,
            [name]: !(value.trim()) ? `${name}을 입력하세요.` : "",
        }));
    }

    const handleSigninInputOnChange = (e) => { // 입력값 최신화
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value,
        });
    }

    const handleSigninButtonOnClick = async () => {
        if(Object.entries(errors).filter(entry => !!entry[1]).length > 0) {
        // Object.entries() : 객체의 키-값 쌍을 배열 형태로 변환
            return;
        }

        try {
            const response = await api.post("/api/auth/signin", signinInput); // signinInput 데이터를 JSON으로 서버에 전달(body)
            const accessToken = response.data.accessToken;
            const refreshToken = response.data.refreshToken;

            setAccessToken(accessToken);
            setRefreshToken(refreshToken);

            queryClient.invalidateQueries({queryKey: ["userQuery"]}); // 캐시 무효화

            setSigninError(false); // 문제 없었으니 false로
            navigate("/");
        } catch(error) {
            setSigninError(true);
        }
    }

    return (
        <Box mt={10}>
            <Container maxWidth={"xs"}>
                <Card variant='outlined'>
                    <CardContent>
                        <Typography variant='h4' textAlign={'center'}>로그인</Typography>
                        <Box display={"flex"} flexDirection={'column'} gap={2}>
                            <TextField type='text' label="username" name='username' 
                                onChange={handleSigninInputOnChange} value={signinInput.username}
                                onBlur={handleInputOnBlur}
                                error={!!errors.username}
                                helperText={errors.username} />
                            <TextField type='password' label="password" name='password' 
                                onChange={handleSigninInputOnChange} value={signinInput.password}
                                onBlur={handleInputOnBlur}
                                error={!!errors.password}
                                helperText={errors.password} />
                            {
                                isSigninError && 
                                <Typography variant='body2' textAlign={'center'} color='red'>
                                    사용자 정보를 다시 확인하세요.
                                </Typography>
                            }
                            
                            <Button variant='contained' onClick={handleSigninButtonOnClick}>로그인</Button>
                        </Box>
                        <Typography variant='h6' textAlign={'center'}>
                            계정이 없으신가요? <Link to={"/signup"}>회원가입</Link>
                        </Typography>
                    </CardContent>
                </Card>
            </Container>
        </Box>
    );
}

export default SigninPage;