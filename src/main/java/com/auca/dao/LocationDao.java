package com.auca.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.auca.domain.Location;
import com.auca.util.HibernateUtil;

public class LocationDao {

    public Location save(Location location) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .buildSessionFactory("application.properties")
                .openSession()) {

            transaction = session.beginTransaction();

            session.persist(location);

            transaction.commit();

            return location;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }
}