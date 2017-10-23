package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;

import java.util.List;

public interface GenericDao<T> {
    /*
* Method writes an entity to the database
*/
    T create(T t) throws DaoException;

    T update(T t) throws DaoException;

    void delete(T t) throws DaoException;

    T findById(Integer id) throws DaoException;

    List<T> findAll() throws DaoException;
}
