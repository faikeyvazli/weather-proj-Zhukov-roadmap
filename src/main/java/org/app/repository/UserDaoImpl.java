package org.app.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.app.model.User;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int register(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        return 1;
    }

    @Override
    public List<User> selectUsers() {
        Session session = this.sessionFactory.openSession();
        try{
            var queryString = "SELECT p FROM User p";
            TypedQuery<User> query = session.createQuery(queryString, User.class);
            return query.getResultList();
        }
       finally{
            /* the null check is important here
            to prevent the NullPointerException
            that might occur when calling isOpen() method
             */

            if (session!=null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        try{
            var queryString = "SELECT p FROM User p WHERE p.login LIKE :username";
            TypedQuery<User> query = session.createQuery(queryString, User.class);
            query.setParameter("username", username);
            return Optional.of(query.getSingleResult());
        }
        catch(NoResultException | NonUniqueResultException e){
            return Optional.empty();
        }
        finally{
            if (session!=null && session.isOpen()){
                session.close();
            }
        }
    }
}
