package com.korit.controller;

import com.korit.entity.User;
import com.korit.repository.UserRepository;
import com.korit.service.UserService;
import com.korit.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // All 아님. Required 임
public class UserController {
    private final UserService userService; // 어차피 계속 쓰니까 전역으로 필드 선언
    // final : userService 이거 반드시 있어야 하기때문에 초기화 유도(final의 조건)
    // userService를 바꾸고싶다면 Required가 아니라 All로 두고 @Setter 사용
    public void signIn() {

    }

    public void signUp() { //UserServiceImpl 이 아닌 UserService 으로. 업캐스팅
        String username = "test";
        String password = "1234";
        String email = "test@gmail.com";
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
//        UserServiceImpl userService = new UserServiceImpl();
// UserServiceImpl가 없으면 동작이 안됨. UserController가 UserServiceImpl을 의존

        userService.add(user);
    }

}
