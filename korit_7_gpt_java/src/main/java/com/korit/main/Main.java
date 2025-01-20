package com.korit.main;

import com.korit.controller.UserController;
import com.korit.service.UserServiceImpl;
import com.korit.service.UserServiceImpl2;

public class Main {
    public static void main(String[] args) {
        // DI. Dependency I...?
        UserServiceImpl userService = new UserServiceImpl(); // 생성을 따로따로
        UserServiceImpl2 userService2 = new UserServiceImpl2(); // 생성을 따로따로
        UserController userController = new UserController(userService); // 여기서 매개변수로 넣으면 더 이상 바꿀 수 없다 그러면 이제 밑에서 매개변수 입력없이 그냥 사용 가능
//        userController.signUp(userService);
//        userController.signIn(userService); // 계속 이렇게 새로 넣어줘야 한다 이런식이면
        userController.signUp(); // 컨트롤러에서 전역필드로 이미 Userservice타입의 필드를 만들었기때문에 이렇게 가능
        userController.signIn();
        System.out.printf("5435345");
        System.out.printf("5435345");

//        userController.setUserService(userService2); // AllArgument, 그리고 @Setter가 있을시
// 필요에 따라 둘을 선택해서 사용하면 된다

    }
}
