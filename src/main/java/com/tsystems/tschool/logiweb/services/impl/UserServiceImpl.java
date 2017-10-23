package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.UserDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.User;
import com.tsystems.tschool.logiweb.services.UserService;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Transactional
    @Override
    public List<User> findAllUsers() throws ServiceException {

        try {
            return userDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void editUser(User editedUser) throws ServiceException {

        try {
            userDao.update(editedUser);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public int addUser(User newUser) throws ServiceException {
        try {
            userDao.create(newUser);
            return newUser.getId();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public User findUserById(int id) throws ServiceException {

        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public User findUserByEmail(String email) throws ServiceException {
        try {
            return userDao.findUserByEmail(email);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
