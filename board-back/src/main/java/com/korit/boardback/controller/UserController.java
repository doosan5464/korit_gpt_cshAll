package com.korit.boardback.controller;

import com.korit.boardback.security.principal.PrincipalUser;
import com.korit.boardback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/me")
    // 로그인 되어있는 유저의 정보를 가져오기?
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) { // @AuthenticationPrincipal : ???
//        PrincipalUser principalUser2 =
//                (PrincipalUser) SecurityContextHolder
//                    .getContext()
//                    .getAuthentication()
//                    .getPrincipal();
//        -->> 이런 과정을 @AuthenticationPrincipal 하나면 가져올 수 있다

//        int userId = principalUser.getUser().getUserId();

        return ResponseEntity.ok().body(principalUser.getUser());
    }

}
