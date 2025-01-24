package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.UserDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;
    private static UserService userService = null;

    private UserService() {
        userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public ResponseDto<?> getUser(int userId) {
        User foundUser = userDao.findByid(userId);
        if (foundUser == null) {
            return ResponseDto.fail("user not found"); // 없는 userId로 조회하는 경우
        }
        return ResponseDto.success(userDao.findByid(userId));
    }

    public List<User> getAllUsers(String searchValue) {
        if(searchValue == null || searchValue.isBlank()) {
            return userDao.findAll();
        }
        return userDao.findAllBySearchValue(searchValue);
    }

    public User addUser(User user) {
        Optional<User> userOptional = userDao.save(user);
        return userOptional.get();
    }
}
