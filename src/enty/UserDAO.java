package enty;


import actions.STATUS;
import hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import org.hibernate.Transaction;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class UserDAO {
    public void save(User transientInstance) {

        try {
            getSession().save(transientInstance);
        } catch (RuntimeException e) {
            throw e;
        }

    }

    public boolean userAuthentication(String email, String password) {

        String querryString = "from User  where email = :email and password = :password ";
        Query querryObject = getSession().createQuery(querryString);
        querryObject.setParameter("email", email);
        querryObject.setParameter("password", password);

        return !querryObject.list().isEmpty();

    }

    public boolean checkUniqueEmail(String email) {
        String querryString = "from User  where email = :email  ";
        Query querryObject = getSession().createQuery(querryString);
        querryObject.setParameter("email", email);

        return querryObject.list().isEmpty();
    }


    public boolean register(User user) {

        if (!checkUniqueEmail(user.getEmail()))
            return false;

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();

        return true;

    }

    public Session getSession() {
        System.out.println("session" + HibernateSessionFactory.getCurrentSession());
        return HibernateSessionFactory.getCurrentSession();
    }
}
