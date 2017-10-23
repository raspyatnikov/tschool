package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.User;

public interface UserDao extends GenericDao<User> {
    User findUserByEmail(String email) throws DaoException;
}
