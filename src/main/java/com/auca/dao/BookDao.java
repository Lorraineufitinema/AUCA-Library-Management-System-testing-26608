package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Book;

public class BookDao {

    private final SessionFactory sessionFactory;

    public BookDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public String save(Book book){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(book);

            transaction.commit();

            return "Book saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Book findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Book.class,id);

        }

    }

}