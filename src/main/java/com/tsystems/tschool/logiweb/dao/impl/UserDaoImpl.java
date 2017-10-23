package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.UserDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public User findUserByEmail(String email) throws DaoException{
        try {
            return em.createNamedQuery("User.findUserByEmail", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
        catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }
}
