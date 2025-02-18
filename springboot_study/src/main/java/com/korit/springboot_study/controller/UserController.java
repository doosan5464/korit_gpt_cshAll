package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddUserDto;
import com.korit.springboot_study.dto.request.ReqModifyUserDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@Api(tags = "사용자 정보 API")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/username/duplication")
    public ResponseEntity<SuccessResponseDto<Boolean>> duplicateUsername(
            @RequestParam
            @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "영어 대소문자 (A-Z, a-z), 숫자 (0-9), 밑줄(_)만 포함 가능합니다.")
            String username) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.duplicateUsername(username)));
    }

    @PostMapping("/api/user")
    @ApiOperation(value = "사용자 추가")
    public ResponseEntity<SuccessResponseDto<User>> addUser(@Valid @RequestBody ReqAddUserDto reqAddUserDto) throws MethodArgumentNotValidException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.addUser(reqAddUserDto)));
    }

    @GetMapping("/api/user/{userId}")
    @ApiOperation(value = "사용자 ID로 조회")
    public ResponseEntity<SuccessResponseDto<User>> getUser(
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getUserById(userId)));
    }

    @GetMapping("/api/users")
    @ApiOperation(value = "사용자 정보 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<User>>> getUsers() throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getAllUsers()));
    }

    @PutMapping("/api/user/{userId}")
    @ApiOperation(value = "사용자 수정")
    public ResponseEntity<SuccessResponseDto<?>> modifyUser(
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId,
            @Valid @RequestBody ReqModifyUserDto reqModifyUserDto
    ) throws NotFoundException, MethodArgumentNotValidException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.modifyUser(userId, reqModifyUserDto)));
    }

    @DeleteMapping("/api/user/{userId}")
    @ApiOperation(value = "사용자 정보 삭제")
    public ResponseEntity<SuccessResponseDto<?>> deleteUser(
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.deleteUser(userId)));
    }

    @GetMapping("/api/usertemp/{roleId}")
    @ApiOperation(value = "Role ID로 사용자 조회")
    public ResponseEntity<SuccessResponseDto<List<User>>> getUsers(
            @Min(value = 1, message = "Role ID는 1이상의 정수입니다")
            @PathVariable int roleId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getUserByRoleId(roleId)));
    }


}