package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void editUser(User editedUser);

    int addUser(User newUser);
    
    User findUserById(int id);
}
