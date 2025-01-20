package com.korit.service;

import com.korit.entity.User;

public interface UserService {
    void add(User user);
    void remove();
    void printInfo();
}
