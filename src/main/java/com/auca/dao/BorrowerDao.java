package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    public long countActiveBorrows(UUID readerId){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "select count(b) from Borrower b where b.reader.personId=:readerId and b.returnDate is null",
                    Long.class)
                    .setParameter("readerId", readerId)
                    .uniqueResult();

        }

    }

}