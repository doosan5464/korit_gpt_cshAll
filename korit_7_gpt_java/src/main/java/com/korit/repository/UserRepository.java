package com.korit.repository;

import com.korit.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance;
    private List<User> userList;

    private UserRepository() {
        userList = new ArrayList<User>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            return instance;
        } else {
            return instance;
        }
    }
    public void addUser(User user) {
        userList.add(user);
    }
}
