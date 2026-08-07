package com.auca.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Room;

public class RoomDao {

    private final SessionFactory sessionFactory;

    public RoomDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public String save(Room room){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(room);

            transaction.commit();

            return "Room saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Room findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Room.class,id);

        }

    }

    public List<Room> findAll(){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery("from Room", Room.class).list();

        }

    }

}