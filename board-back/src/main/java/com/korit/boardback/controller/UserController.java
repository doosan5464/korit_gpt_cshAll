package com.korit.boardback.controller;

import com.korit.boardback.security.principal.PrincipalUser;
import com.korit.boardback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {
    // Principal -> 사용자의 정보를 담고 있는 기본 인터페이스

//        PrincipalUser principalUser2 =
//                (PrincipalUser) SecurityContextHolder
//                    .getContext()
//                    .getAuthentication()
//                    .getPrincipal();      => 이런 과정 없이 @AuthenticationPrincipal 쓰면 생략 가능
        if(principalUser.getUser().getProfileImg() == null) {
            principalUser.getUser().setProfileImg("default.png");
        }
        return ResponseEntity.ok().body(principalUser.getUser());
    }

    @PostMapping("/user/profile/img")
    public ResponseEntity<?> changeProfileImg(
            @AuthenticationPrincipal PrincipalUser principalUser,
            // getUser() 로 User 객체로 변환
            @RequestPart MultipartFile file) {
            // @RequestPart : HTTP 요청 본문(body)에서 파일과 같은 부분을 처리할 때 사용 (MultipartFile와 자주 사용)
            // MultipartFile : 클라이언트가 업로드한 파일을 다룰 때 사용

        userService.updateProfileImg(principalUser.getUser(), file);
        return ResponseEntity.ok().build();
    }
}