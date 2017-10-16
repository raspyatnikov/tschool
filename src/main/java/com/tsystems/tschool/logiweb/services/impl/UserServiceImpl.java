package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.UserDao;
import com.tsystems.tschool.logiweb.entities.User;
import com.tsystems.tschool.logiweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void editUser(User editedUser) {

        userDao.update(editedUser);
    }

    @Transactional
    @Override
    public int addUser(User newUser) {
        userDao.create(newUser);
        return newUser.getId();
    }

    @Transactional
    @Override
    public User findUserById(int id) {
        return userDao.findById(id);
    }
}
