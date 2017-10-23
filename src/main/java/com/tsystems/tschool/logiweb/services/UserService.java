package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.User;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    void editUser(User editedUser) throws ServiceException;

    int addUser(User newUser) throws ServiceException;
    
    User findUserById(int id) throws ServiceException;

    User findUserByEmail(String email) throws ServiceException;
}
