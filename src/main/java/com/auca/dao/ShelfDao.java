package com.auca.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Shelf;

public class ShelfDao {

    private final SessionFactory sessionFactory;

    public ShelfDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public String save(Shelf shelf){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(shelf);

            transaction.commit();

            return "Shelf saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Shelf findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Shelf.class,id);

        }

    }

    public String update(Shelf shelf){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.merge(shelf);

            transaction.commit();

            return "Shelf updated";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public List<Shelf> findByRoomId(UUID roomId){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "from Shelf where room.roomId=:roomId",
                    Shelf.class)
                    .setParameter("roomId", roomId)
                    .list();

        }

    }

}