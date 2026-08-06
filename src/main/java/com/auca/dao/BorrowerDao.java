package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Borrower;
import com.auca.domain.Borrower;

public class BorrowerDao {

    private final SessionFactory sessionFactory;

    public BorrowerDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public String save(Borrower borrower){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(borrower);

            transaction.commit();

            return "Borrower saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Borrower findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Borrower.class,id);

        }

    }

}