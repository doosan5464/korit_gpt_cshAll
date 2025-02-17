import { Box, Button, Card, CardContent, Container, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api } from '../../api/config/axiosConfig';

function SignupPage(props) {
    const navigate = useNavigate();

    const [ signupInput, setSignupInput ] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });

    const [ errors, setErrors ] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });

    const handleSignupInputOnChange = (e) => {
        setSignupInput({
            ...signupInput,
            [e.target.name] : e.target.value,
        });
    }

    const handleInputOnBlur = (e) => {
        const { name, value } = e.target;
        let message = "";
        if(name === "username" && !(/[a-zA-Z0-9_]{4,16}$/.test(value))) { // 자바에서는 정규식을 / / 안에 넣어야 함
            message = "영어 대소문자 (A-Z, a-z), 숫자 (0-9), 밑줄(_), 4~16자만 가능합니다."
        }
        if(name === "password" && !(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+~`|{}:;'<>,.?/-])[A-Za-z\d!@#$%^&*()_+~`|{}:;'<>,.?/-]{8,}$/.test(value))) { 
            message = "영어 대소문자, 숫자, 특수문자(!@#$%^&*()_+~`|{}:;'<>,.?/-)를 하나 이상 모두 포함하며 8자리 이상 입력해야합니다."
        }
        if(name === "name" && !(/^[가-힇]{2,}$/.test(value))) { 
            message = "한글 2자 이상만 입력가능합니다."
        }
        if(name === "email" && !(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(value))) {
            message = "올바른 이메일 주소를 입력하세요."
        }
            
        setErrors({
            ...errors,
            [name]: message // 원래도 공백, 오류가 있다면 값이 생김
        });
    }

    const handleSignupButtonOnClick = async () => {
        if(Object.entries(errors).filter(entry => !!entry[1]) > 0) {  // Object.entries(obj) : 객체의 키-값 쌍을 배열의 형태로 반환하는 함수
                                                                      // .filter로 키-값에서 값만 들고오고 문자열이지만
                                                                      // 앞에 !!가 붙어서 빈 배열이면 false -> 0, 배열에 뭔가 있다면 true -> 1 그래서 코드가 동작
            return;
        }
        try {
            await api.post("/api/auth/signup", signupInput); // 앞-head, 뒤-body, 앞부분 : URL (API 경로, head), 뒷부분 : 요청 바디 (body), 보통 서버에 보내는 데이터
            alert("회원가입 완료.");
            navigate("/signin");
        } catch (error) { // 중복확인은 가입하기 누를 때 1번만
            setErrors({
                username: error.response.data.data,
                password: "",
                name: "",
                email: "",
            })
        }
    }
    /*
    Axios와 같은 HTTP 클라이언트에서 API 요청에 실패했을 때 발생하는 에러 객체의 구조

    error.response → HTTP 응답 전체
        {
            status: 400,             // HTTP 상태 코드 (400 Bad Request)
            statusText: 'Bad Request',  // 상태 메시지
            headers: { ... },        // 응답 헤더
            data: { ... }            // 실제 응답 본문 데이터 (즉, 서버에서 보낸 데이터)
        }
    error.response.data → 백엔드가 보낸 JSON 데이터
        {
            "message": "요청한 데이터가 잘못되었습니다.",
            "code": "BAD_REQUEST",
            "data": { 
                "username": "아이디가 이미 존재합니다.", 
                "email": "이메일 형식이 잘못되었습니다." 
            }
        }
    error.response.data.data → 백엔드에서 data 필드 안에 담은 유효성 검사 에러 목록
        {
            "message": "유효성 검사 오류",
            "code": "VALIDATION_ERROR",
            "data": {
                "username": "아이디는 4~16자여야 합니다.",
                "password": "비밀번호는 최소 8자 이상이어야 합니다."
            }
        }
    */



    // const handleSignupButtonOnClick = async () => {
    //     try {
    //         const response = await api.post("/api/auth/signup", signupInput);  
    //         console.log(response.data);                                        
    //                                                                                          
    //     } catch (error) {
    //         console.log(error.response.data.data);
    //         let newError = {};
    //         const responseErrors = error.response.data.data;
    //         for(let e of responseErrors) {
    //             const errorEntry = Object.entries(e)[0]; // 에러 반환타입이 Map이어서 이렇게
    //             newError = {
    //                 ...newError,
    //                 [errorEntry[0]]: errorEntry[1],
    //             };
    //         }
    //         setErrors({
    //             username: "",
    //             password: "",
    //             name: "",
    //             email: "",
    //             ...newError
    //         });
    //     }
    // }
    console.log(errors);

    return (
        <Box mt={10}>
            <Container maxWidth={"xs"}>
            <Card variant='outlined'>
                <CardContent>
                    <Typography variant='h4' textAlign={'center'}>회원가입</Typography>
                    <Box display={"flex"} flexDirection={'column'} gap={2}>
                        <TextField type='text' label="username" name= 'username' 
                        onChange={handleSignupInputOnChange} value={signupInput.username}
                        onBlur={handleInputOnBlur}
                        error={!!errors.username}
                        helperText={errors.username} />
                        <TextField type='password' label="password" name= 'password' 
                        onChange={handleSignupInputOnChange} value={signupInput.password}
                        onBlur={handleInputOnBlur}
                        error={!!errors.password}
                        helperText={errors.password}
                        />
                        <TextField type='text' label="name" name= 'name' 
                        onChange={handleSignupInputOnChange} value={signupInput.name}
                        onBlur={handleInputOnBlur}
                        error={!!errors.name}
                        helperText={errors.name}
                        />
                        <TextField type='email' label="email" name= 'email' 
                        onChange={handleSignupInputOnChange} value={signupInput.email}
                        onBlur={handleInputOnBlur}
                        error={!!errors.email}
                        helperText={errors.email}
                        />
                        <Button variant='contained' onClick={handleSignupButtonOnClick}>가입하기</Button>
                    </Box>
                    <Typography variant='h6' textAlign={'center'}>이미 계정이 있나요? <Link to={"/signin"}>로그인</Link> </Typography>
                </CardContent>
            </Card>
            </Container>
        </Box>
    );
}

export default SignupPage;