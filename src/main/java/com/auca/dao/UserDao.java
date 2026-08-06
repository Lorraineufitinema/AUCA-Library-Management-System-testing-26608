package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.User;

public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String save(User user) {

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();

            return "User saved successfully";

        }catch(RuntimeException ex){

            if(transaction != null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public User findById(UUID id){

        try(Session session = sessionFactory.openSession()){

            return session.get(User.class, id);

        }

    }

    public User findByUsername(String username){

        try(Session session = sessionFactory.openSession()){

            return session.createQuery(
                    "from User u where u.username=:username",
                    User.class)
                    .setParameter("username", username)
                    .uniqueResult();

        }

    }

}