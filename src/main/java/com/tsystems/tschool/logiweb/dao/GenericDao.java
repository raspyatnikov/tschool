package com.tsystems.tschool.logiweb.dao;

import java.util.List;

public interface GenericDao<T> {
    /*
* Method writes an entity to the database
*/
    T create(T t);

    T update(T t);

    void delete(T t);

    T findById(Integer id);

    List<T> findAll();
}
