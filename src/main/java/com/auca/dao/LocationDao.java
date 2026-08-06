package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Location;

public class LocationDao {

    private final SessionFactory sessionFactory;

    public LocationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String save(Location location){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(location);

            transaction.commit();

            return "Location saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Location findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Location.class,id);

        }

    }

    public Location findByCode(String code){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "from Location where code=:code",
                    Location.class)
                    .setParameter("code",code)
                    .uniqueResult();

        }

    }

}