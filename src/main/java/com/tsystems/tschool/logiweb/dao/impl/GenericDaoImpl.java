package com.tsystems.tschool.logiweb.dao.impl;
import com.tsystems.tschool.logiweb.dao.GenericDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @PersistenceContext
    protected EntityManager em;
    protected Class entityClass;


    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass
                .getActualTypeArguments()[0];
    }


    @Override
    public T create(T t) throws DaoException{
        try {
            em.persist(t);
            return t;
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }

    /**
     * This method updates an entity in the database.
     */
    @Override
    public T update(T t) throws DaoException{
        try {
            em.merge(t);
            return t;
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }


    @Override
    public void delete(T t) throws DaoException {
        try {
            t = em.merge(t);
            em.remove(t);
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }

    }


    @Override
    public T findById(Integer id) throws DaoException{
        try {
            return (T) em.find(entityClass, id);
        }
        catch (NoResultException e){
            return null;
        }
        catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }

    }


    @Override
    public List<T> findAll() throws  DaoException{

        try {
            TypedQuery<T> query = em.createQuery("from " + entityClass.getName(), entityClass);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }
}
