package com.korit.service;

import com.korit.entity.User;
import com.korit.repository.UserRepository;

public class UserServiceImpl implements UserService {
    @Override
    public void add(User user) {
//        UserRepository userRepository = UserRepository.getInstance();
//        userRepository.addUser(user);
        UserRepository.getInstance().addUser(user); // 이렇게하면 위에가 필요 없음
    }

    @Override
    public void remove() {

    }

    @Override
    public void printInfo() {

    }
}
